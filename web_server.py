# -*- encoding:utf-8 -*-

import sys
from importlib import reload

reload(sys)
from jinja2 import evalcontextfilter
from markupsafe import Markup
import re
import collections
import copy
import time
import socket
import json
import pprint
import MedicineSearch
import DB
import sqlite3
from flask import Flask
from flask import request, url_for
from flask import render_template, redirect, make_response
import threading
import os
import datetime

app = Flask(__name__, static_url_path="/image", static_folder="image")

UPLOAD_FOLDER = './image'
ALLOWED_EXTENSIONS = {'txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'}
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
_paragraph_re = re.compile(r'(?:\r\n|\r|\n){2,}')


def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS


@app.template_filter()
@evalcontextfilter
def nl2br(eval_ctx, value):
    _paragraph_re = re.compile(r'(?:\r\n|\r(?!\n)|\n){2,}')
    result = u'\n\n'.join(u'<p>%s</p>' % p.replace(u'\r\n', u'<br/>') for p in _paragraph_re.split(value))
    print(result)
    if eval_ctx.autoescape:
        result = Markup(result)
    return result


@app.route('/')
def hello_world():
    result = {}
    if "userID" in request.cookies:
        result["login"] = {"bool": "true"}
        result["memos"] = json.loads(DB.search_memo(request.cookies.get("userID"), "*"))
        if len(result["memos"]) < 4:
            result["memos1"] = result["memos"]
            result["memos2"] = []
        else:
            result["memos1"] = result["memos"][:4]
            if len(result["memos"]) < 8:
                result["memos2"] = result["memos"][4:]
            else:
                result["memos2"] = result["memos"][4:8]
    else:
        print(2)
        result["login"] = {"bool": "false"}
        result["memos1"] = []
        result["memos2"] = []
    print(result)
    return render_template('home.html', result=result)


@app.route('/report')
def report_list():
    alarm = json.loads(DB.medicine_search(request.cookies.get("userID")))
    print(alarm)
    result = {}
    if "userID" in request.cookies:
        result["login"] = {"bool": "true"}
    else:
        result["login"] = {"bool": "false"}
    result["medicine"] = alarm
    return render_template("report_list.html", result=result)


@app.route('/report/instant/<hash_value>')
def instant_report(hash_value):
    medicine_name = json.loads(DB.instant_report_search(hash_value))
    print(DB.instant_report_delete(hash_value))
    user_id = medicine_name['user_id']
    medicine_name = medicine_name['medicine_name']

    print(user_id, medicine_name)

    taken = DB.medicine_taking(user_id, str(medicine_name), "19700101",
                               datetime.datetime.fromtimestamp(time.time()).strftime('%Y%m%d'))
    print(taken)
    taken = json.loads(taken)
    print(taken)
    result = {}
    total = {}

    total["memos"] = json.loads(DB.search_memo(user_id, medicine_name))

    for item in taken['record']:
        tmp = item['Date'].split(' ')[1].split(':')
        timeConversion = float(tmp[0]) + float(tmp[1]) / 60.0 + float(tmp[2]) / 3600.0
        try:
            result[item['Date'].split(' ')[0]].insert(0, timeConversion)
        except:
            result[item['Date'].split(' ')[0]] = [timeConversion]
    result2 = copy.deepcopy(result)
    for key in result2.keys():
        result2[key].reverse()
    result = collections.OrderedDict(sorted(result.items()))
    result2 = collections.OrderedDict(sorted(result2.items()))
    print(result)
    try:
        maxLen = max(map(len, result.values()))
    except Exception as e:
        maxLen = 0
    for key, value in result.items():
        if len(value) < maxLen:
            value += [0] * (maxLen - len(value))
    for key, value in result2.items():
        if len(value) < maxLen:
            value += [0] * (maxLen - len(value))
    total["result"] = result
    total["result2"] = result2
    total["length"] = maxLen
    total["login"] = {"bool": "true"}
    print(total["memos"])
    return render_template('report.html', total=total)


@app.route('/report/<medicine_name>', )
def report(medicine_name):
    taken = DB.medicine_taking(str(request.cookies.get('userID')), str(medicine_name), "19700101",
                               datetime.datetime.fromtimestamp(time.time()).strftime('%Y%m%d'))
    taken = json.loads(taken)
    print(taken)
    result = {}
    total = {}

    total["memos"] = json.loads(DB.search_memo(request.cookies.get("userID"), medicine_name))

    for item in taken['record']:
        tmp = item['Date'].split(' ')[1].split(':')
        timeConversion = float(tmp[0]) + float(tmp[1]) / 60.0 + float(tmp[2]) / 3600.0
        try:
            result[item['Date'].split(' ')[0]].insert(0, timeConversion)
        except:
            result[item['Date'].split(' ')[0]] = [timeConversion]
    result2 = copy.deepcopy(result)
    for key in result2.keys():
        result2[key].reverse()
    result = collections.OrderedDict(sorted(result.items()))
    result2 = collections.OrderedDict(sorted(result2.items()))
    print(result)
    try:
        maxLen = max(map(len, result.values()))
    except Exception as e:
        maxLen = 0
    for key, value in result.items():
        #        for idx in range(1, len(value)):
        #            value[idx] = value[idx] - value[idx-1]
        if len(value) < maxLen:
            value += [0] * (maxLen - len(value))
    for key, value in result2.items():
        if len(value) < maxLen:
            value += [0] * (maxLen - len(value))
    total["result"] = result
    total["result2"] = result2
    total["length"] = maxLen
    total["login"] = {"bool": "true"}
    print(total["memos"])
    return render_template('report.html', total=total)


@app.route('/login', methods=['POST', 'GET'])
def login():
    if request.method == "GET":
        return render_template("login.html", result={"login": "false", "result": {"result": "None"}})
    elif request.method == "POST":
        userID = request.form['userID']
        userPW = request.form['userPW']
        result = {}
        res = json.loads(DB.user_login(userID, userPW))
        if res['result'] == "Yes":
            result['login'] = 'true'
            result['result'] = res
            response = make_response(redirect("/"))
            response.set_cookie("userID", userID)
            return response
        else:
            return redirect("/login")


@app.route('/register', methods=['POST', 'GET'])
def register():
    if request.method == "GET":
        return render_template("register.html", res={"result": ""})
    elif request.method == "POST":
        userID = request.form['userID']
        userPW = request.form['userPW']
        userREPW = request.form['userREPW']
        res = json.loads(DB.user_validation(userID))
        if res['result'] == "No":
            return render_template("register.html", res={"result": "Duplicate"})
        elif len(userID) < 4:
            return render_template("register.html", res={"result": "Short"})
        elif userPW != userREPW:
            return render_template("register.html", res={"result": "Different"})
        else:
            DB.user_register(userID, userPW)
            return redirect("/login")


@app.route('/logout', methods=["GET"])
def logout():
    response = make_response(redirect('/'))
    response.set_cookie("userID", '', expires=0)
    return redirect("/")


@app.route('/memo/write', methods=['POST', 'GET'])
def memo_write():
    if "userID" in request.cookies:
        if request.method == "GET":
            return render_template("Memo_write.html")
        elif request.method == "POST":
            image = request.files['image']
            memo_user = request.cookies.get("userID")
            memo_text = request.form['memo_text']
            cur_time = float(time.time())

            if image and allowed_file(image.filename):
                image.save(os.path.join(app.config['UPLOAD_FOLDER'],
                                        memo_user + datetime.datetime.fromtimestamp(cur_time).strftime(
                                            "%Y-%m-%d-%H-%M-%S") + ".jpeg"))
            DB.web_insert_memo(memo_user, cur_time, memo_text,
                               "image/" + memo_user + datetime.datetime.fromtimestamp(cur_time).strftime(
                                   "%Y-%m-%d-%H-%M-%S"))
            return redirect("/memo")
    else:
        return redirect("/login")


@app.route('/memo')
def memo_list():
    result = {}
    if "userID" in request.cookies:
        result["login"] = {"bool": "true"}
        result["memos"] = json.loads(DB.search_memo(request.cookies.get("userID"), "*"))
        if len(result["memos"]) < 4:
            result["memos1"] = result["memos"]
            result["memos2"] = []
        else:
            result["memos1"] = result["memos"][:4]
            if len(result["memos"]) < 8:
                result["memos2"] = result["memos"][4:]
            else:
                result["memos2"] = result["memos"][4:8]
    else:
        result["login"] = {"bool": "false"}
        result["memos1"] = []
        result["memos2"] = []
        return redirect("/login")
    return render_template('Memo_list.html', result=result)


@app.route('/search/medicine/<medicine_link>')
def specific_medicine(medicine_link):
    medicine_link = "./" + medicine_link + "?item_Seq=" + request.args.get("item_Seq")
    result = {}
    result["medicine"] = json.loads(MedicineSearch.crawler(medicine_link))
    result["login"] = {"bool": "None"}
    return render_template('Medicine_info.html', result=result)


@app.route('/search/medicine', methods=['POST'])
def search_medicine():
    medicine_name = request.form['medicine_name']
    result = {}
    result["medicines"] = json.loads(MedicineSearch.crawler(medicine_name))
    result["login"] = {"bool": "None"}
    pprint.pprint(result)
    return render_template('Medicine_list.html', result=result)


def smartphone_connection():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind(("", 6000))
    sock.listen(5)
    try:
        while True:
            client, addr = sock.accept()
            req = client.recv(1024)
            print(req)
            req = json.loads(req)
            if req['Type'] == 'Search_Medicine':
                client.send(MedicineSearch.crawler(req["Name"]))
            if req['Type'] == 'Write_Memo':
                client.send("1\n".encode())
                print(addr)
                length = int(client.recv(1024))
                client.send("1\n".encode())
                image = ""
                print(length)
                if length % 1024 == 0:
                    length /= 1024
                else:
                    length = length / 1024 + 1
                print(length)
                while length > 0:
                    length -= 1
                    image += client.recv(1024)
                    client.send("1\n")
                print(image)
                client.send(DB.insert_memo(req['Id'], time.time(), req['Text'], image, req["Medicine_Name"]))
            if req['Type'] == 'Search_Memo':
                client.send(DB.search_memo(req['User'], req['Medicine_Name']))
            if req['Type'] == "Register":
                client.send(DB.user_register(req['Id'], req['Password']))
            if req['Type'] == "Validation":
                client.send(DB.user_validation(req['Id']))
            if req['Type'] == 'Login':
                client.send(DB.user_login(req['Id'], req['Password']))
            if req['Type'] == 'Edit_Memo':
                client.send(DB.search_memos(req['Id'], req['Position'], req["Medicine_Name"]))
            if req['Type'] == 'Edit_Content':
                client.send("1\n")
                length = int(client.recv(1024))
                client.send("1\n")
                image = ""
                print(length)
                if length % 1024 == 0:
                    length /= 1024
                else:
                    length = length / 1024 + 1
                while length > 0:
                    length -= 1
                    image += client.recv(1024)
                    client.send("1\n")
                print(image)
                client.send(
                    DB.change_memo(req['Id'], req['Position'], req['Text'], image, req["Medicine_Name"], time.time()))
            if req['Type'] == 'Delete_Memo':
                client.send(DB.delete_memo(req['Id'], req['Position']))
            if req['Type'] == 'Medicine_Record':
                client.send(DB.medicine_taking(req['Id'], req['Medicine_Name'], req['From'], req['To']))
            if req["Type"] == "Medicine_Taken":
                client.send(DB.medicine_taken(req["Id"], req["Medicine_Name"], req["Date"]))
            if req["Type"] == "Add_Medicine":
                client.send(DB.medicine_add(req["Id"], req["Medicine_Name"]))
            if req["Type"] == "Create_Instant_Report":
                client.send(DB.instant_report_insert(req['Hash'], req['Medicine_Name'], req['Id']))
            client.close()
    except Exception as e:
        print(e)
        sock.close()


if __name__ == '__main__':
    threading.Thread(target=smartphone_connection).start()
    app.run(host="")
    # unittest.main()
