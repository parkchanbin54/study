#!/usr/bin/env python
# coding: utf-8

# In[1]:


def solution(price, money, count):
    if price>2500 or price<1:
        return -1;
    if money>1000000000 or money<1:
        return -1;
    if count<1 or count>2500:
        return -1;
    
    tmptotal=0;
    while count>0:
        tmptotal+=price*count;
        count-=1;
    
    answer = tmptotal-money
    
    if answer<0:
        return 0;

    return answer


# In[ ]:




