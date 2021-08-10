#!/usr/bin/env python
# coding: utf-8

# In[86]:


def solution(scores):
    b=[0]*len(scores)
    result=[0]*len(scores[0])
    for i in range(len(scores)):
        b[i]=[row[i] for row in scores] 
    for i in range(len(b[0])):
        total=0;
        check=0;
        if max(b[i])==b[i][i] or min(b[i])==b[i][i]:
            
            check=1;
        for j in range(len(b)):
            if b[i][i]==b[i][j] and i!=j:
                check=0;
            total+=b[i][j];
        if check==1:
            result[i]=(total-b[i][i])/(len(b)-1);   
        else:
            result[i]=total/len(b);
        
    answer='';

    for i in range(len(result)):
        if result[i]>=90:
            answer+=('A');
            continue;
        elif result[i]>=80:
            answer+=('B');
            continue;
        elif result[i]>=70:
            answer+=('C');
            continue;
        elif result[i]>=50:
            answer+=('D');
            continue;
        else:
            answer+=('F');

    return answer


# In[15]:




