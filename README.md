Two spring boot/cloud micro-services based on gradle are created.

Both micro-services are registered in Consul ( HashiCorp )

External micro-service is calling the internal one using Feign with service discovery  provided by Consul.

External micro-service is exposing ReST endpoints:   

  GET /message       
    returns all messages  
  POST /message       
    adds a message body: {"message":"some text"}    
    and returns id created for this new message {"id":1}  
  GET /message/{id}    
    returns specific message by id        
      {"message":"some text", "id": 1}    
  DELETE /message/{id}    
    confirms deletion
    
 The internal one is implementing same endpoints as external.
