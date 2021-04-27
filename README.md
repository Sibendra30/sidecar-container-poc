# sidecar-container-poc  #k8-Design Pattern (Sidecar or Sidekick design pattern)
Here we are creating a separate container (responsible for getting the token and refresh at certain intervals) any independent task which is can be handled independently and deploy alongside the main container. Whereas main container can always refer to sidecar container for auth token.


Created two springboot app.

1. Main app  - Consumer of auth token. 

2. Auth token generator app - Producer of auth token

Mounted a empty directory volume inside pod which is refereed by both of the containers (apps). Main app is always watching for change in value of the token at specified path and file whereas sidecar app is having a scheduler which refreshes the token every N time_unit as configured. 
