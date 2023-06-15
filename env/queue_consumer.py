import pika, os, json

#url = os.environ.get('CLOUDAMQP_URL', 'amqp://sisgeodef:sisgeodef@rabbitmq/%2f')
url =  'amqp://sisgeodef:sisgeodef@rabbitmq/%2f'
params = pika.URLParameters(url)
params.socket_timeout = 5

connection = pika.BlockingConnection(params) # Connect to CloudAMQP
channel = connection.channel() # start a channel
channel.queue_declare(queue='main_channel', durable=True) 

def callback(ch, method, properties, body):
    payload = json.loads(body)
    print( payload['currentAzimuth'] )

channel.basic_consume('main_channel', callback, auto_ack=True)   
channel.start_consuming()

# Publish
#channel.basic_publish(exchange='', routing_key='pdfprocess', body='User information')
#print ("[x] Message sent to consumer")
#connection.close()