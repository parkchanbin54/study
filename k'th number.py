#!/usr/bin/env python
# coding: utf-8


  
  
def solution(array,commands):
    answer=[]
    for k in range (len(commands)):
        s=array[commands[k][0]-1:commands[k][1]];
        for i in range (len(s)):
            for j in range (len(s)-1):
                if s[j]>s[i]:
                    tmp=s[j];
                    s[j]=s[i];
                    s[i]=tmp;
        print(commands[k][2])
        print(s)
        answer.append(s[(commands[k][2])-1]);
    return answer


# In[77]:


solution([1, 5, 2, 6, 3, 7, 4],[[2, 5, 3], [4, 4, 1], [1, 7, 3]])

