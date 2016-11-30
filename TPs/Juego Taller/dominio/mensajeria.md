## Creacion de avatar
El usuario se conecta y crea su avatar
```
{"idUsuario":0,"username":"alan","password":"81dc9bdb5","avatar":{}}
```

## Seleccion de partida
El usuario selecciona una partida del servidor.
```
{"idUsuario":0,"username":"alan","password":"81dc9bdb5","partida":{"idPartida"}}
```

## Eleccion de raza y casta 
El usuario una vez conectado, se crea un personaje eligiendo la raza y casta
```
{"vida":100,"nivel":1,"experiencia":0,"energia":100,"ataque":12,"usuarioPersonaje":{"idUsuario":0,"username":"alan","password":"81dc9bdb52"},"raza":"Orco","casta":{"nombre":"","habilidades":""}}
```

## Formacion alianza
El personaje interactua con otro y forman una alianza
```
{"objOrco":{"usuarioPersonaje":{"idUsuario":0,"username":"alan","password":"81dc9bdb52"},"raza":"Orco"}, "objHumano": {"usuarioPersonaje":{"idUsuario":1,"username":"luis","password":"81dc9bdb52"},"raza":"humano", "alianza":{"warfare"}}}
```

## Union a una alianza
El personaje se une a una alianza ya existente
```
{"objOrco":{"usuarioPersonaje":{"idUsuario":0,"username":"alan"},"raza":"Orco"},"alianza":{"Comegatos","integrantes":{{"idUsuario":0,"username":"elnegro"},"raza":"Orco"}}}
```

## combate con otro personaje
El personaje puede pelearse con otro usando algun ataque
```
{"objOrco":{"usuarioPersonaje":{"idUsuario":1},"raza":"Orco","ataque":{"Ganchodegoro"}},"objElfo":{"usuarioPersonaje":{"idUsuario":2},"raza":"Elfo","ataque":{"furiaSangrienta"}}}
```

## combate entre alianza vs alianza
Los miembros de una alianza pelean con los miembros de otra alianza.
```
{"alianza":{"Comegatos","integrantes":{{"idUsuario":0,"username":"elnegro"},"raza":"Orco"}},"alianza":{"la95","integrantes":{{"idUsuario":1,"username":"player"},"raza":"Elfo"}}}
```

## combate alianza vs generico
Una alianza pelea contra un generico
```
{"alianza":{"la95","integrantes":{{{"idUsuario":1,"username":"player"},"raza":"orco"},{{"idUsuario":2,"username":"pepo"},"raza":"Elfo"}}},"generico":{"vida":"100","nivel":"1","energia":"100"}}
```

## combate entre personaje vs generico
Un personaje pelea contra un generico
```
{"objOrco":{"usuarioPersonaje":{{"idUsuario":0,"username":"alan"},"raza":"Orco"}},"generico":{"vida":"100","nivel":"1","energia":"100"}}
```

## Movimientos del personaje
Por cada movimiento el servidor debe responder en que posicion tiene que aparecer el personaje
```
{"objOrco":{"usuarioPersonaje":{{"idUsuario":0,"username":"alan"},"raza":"Orco"}},{"Movimiento":{"X","Y"}}}
```
