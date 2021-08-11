#!/usr/bin/env python
# coding: utf-8

# In[1]:


def solution(a, b):
    """answer = 0;
    
    if len(a)>1000 or len(a)<1 or len(b)>1000 or len(b)<1:
        return -1;

    for i in range(len(a)):
        if a[i]>1000 or a[i]<-1000 or b[i]>1000 or b[i]<-1000:
            return -1
        answer+=a[i]*b[i];
    
    
    
    return answer"""
    return sum([x*y for x, y in zip(a,b)])


# In[ ]:




