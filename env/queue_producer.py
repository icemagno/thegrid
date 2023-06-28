#!/usr/bin/env python
# coding: utf-8

# In[96]:


import pika, os, json, uuid

url =  'amqp://thegrid:thegrid@cmabreu.com.br:36317/%2f'
params = pika.URLParameters(url)
params.socket_timeout = 5

connection = pika.BlockingConnection(params) 
channel = connection.channel() 
channel.queue_declare(queue='main_channel', durable=True) 


# In[93]:


import uuid
airplaneId = str(uuid.uuid4())
print( airplaneId )


# In[94]:


protocol = { "command" : "PC_SPAWN", "lat" : -23, "lon" : -43, "alt" : 5000, "throttle" : 10, "uuid" : airplaneId }
# Publish
channel.basic_publish(exchange='', routing_key='main_channel', body= json.dumps(protocol))
print ("[x] Message sent. ID: ", airplaneId)


# In[130]:


protocol = { "command" : "PC_RIGHT", "uuid" : airplaneId, "value" : 15 }
# Publish
channel.basic_publish(exchange='', routing_key='main_channel', body=json.dumps(protocol))
print ("[x] Message sent. ID: " , airplaneId)


# In[131]:


protocol = { "command" : "PC_LEFT", "uuid" : airplaneId, "value" : 5 }
# Publish
channel.basic_publish(exchange='', routing_key='main_channel', body=json.dumps(protocol))
print ("[x] Message sent. ID: " , airplaneId)


# In[97]:


protocol = { "command" : "PC_UP", "uuid" : airplaneId, "value" : 500 }
# Publish
channel.basic_publish(exchange='', routing_key='main_channel', body=json.dumps(protocol))
print ("[x] Message sent. ID: " , airplaneId)


# In[107]:


protocol = { "command" : "PC_DOWN", "uuid" : airplaneId, "value" : 500 }
# Publish
channel.basic_publish(exchange='', routing_key='main_channel', body=json.dumps(protocol))
print ("[x] Message sent. ID: " , airplaneId)


# In[121]:


protocol = { "command" : "PC_THROTTLE", "uuid" : airplaneId, "value" : 50 }
# Publish
channel.basic_publish(exchange='', routing_key='main_channel', body=json.dumps(protocol))
print ("[x] Message sent. ID: " , airplaneId)


# In[ ]:


connection.close()

