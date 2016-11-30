## **Historias de usuario** ##  

> Como Jugador, Quiero crear mi personaje, editando sus características principales (como raza y casta) 
> Resultado: Quiero verme reflejado en mi avatar  

 *Criterios de aceptación:*  

1. 	Titulo: Seleccionar raza 
	Dado un jugador
	Cuando el jugador quiera editar su raza
	Entonces se mostrarán tres opciones: Orco, Elfo y Humano.
	
2. 	Titulo: Seleccionar casta  
	Dado un jugador
	Cuando el jugador quiera editar su casta
	Entonces se mostrarán tres opciones: Hechicero, Guerrero y Chaman.
	
3.	Titulo: Raza seleccionada  
	Dado un jugador
	Cuando el jugador seleccione una raza
	Entonces se guardará la nueva raza del personaje.

4.	Titulo: Casta seleccionada  
	Dado un jugador
	Cuando el jugador seleccione una casta
	Entonces se guardará la nueva casta del personaje. 
	
5.	Titulo: Personaje editado  
	Dado un jugador
	Cuando el jugador finaliza la edición del personaje
	Entonces se guardará el nuevo personaje creado.

> Como Jugador, Quiero ingresar a un mundo  
> Resultado: Para adquirir experiencia, items y habilidades nuevas  
	
 *Criterios de aceptación:*  
	
1.	Titulo: Seleccionar mundo 
	Dado un jugador
	Cuando el jugador desee comenzar una partida
	Entonces se creará un mundo con fantasmas genéricos, lugares genéricos y aliados si ya ingresaron al mismo sitio. 

2.	Titulo: Ver mundos disponibles
	Dado un jugador
	Cuando un jugador quiera seleccionar un mundo
	Entonces se traerá un listado con los mundos disponibles.  

3.	Titulo: Obtener experiencia y habilidad 
	Dado un jugador y un enemigo 0 fantasma genérico
	Cuando el jugador elimine a su enemigo o fantasma genérico
	Entonces el jugador obtendrá experiencia y habilidades.

4.	Titulo: Obtener mejor item
	Dado un jugador y un enemigo
	Cuando el jugador derrote a su enemigo.  
	Entonces el jugador obtendrá el mejor ítem de su enemigo derrotado.

> Como Orco, Quiero atacar para ganar experiencia  
> Resultado: Para aumentar mi experiencia  

 *Criterios de aceptación:*  
 
1.	Titulo: Quiero derrotar personajes para ganar experiencia. 
	Dado un Orco y enemigos o fantasmas genéricos
	Cuando el Orco derrote enemigos o fantasmas genéricos
	Entonces el Orco incrementará su experiencia de acuerdo al nivel de los derrotados.

/*Repetido*/
> Como Humano, Quiero atacar para ganar experiencia  
> Resultado: Para aumentar mi experiencia  

 *Criterios de aceptación:*  
 
1.	Titulo: Quiero derrotar personajes para ganar experiencia . 
	Dado un Humano y enemigos o fantasmas genéricos
	Cuando el Humano derrote enemigos o fantasmas genéricos
	Entonces el Humano incrementará su experiencia de acuerdo al nivel de los derrotados.

/*Repetido*/
> Como Elfo, Quiero atacar para ganar experiencia  
> Resultado: Para aumentar mi experiencia  

 *Criterios de aceptación:*  
 
1.	Titulo: Quiero derrotar personajes para ganar experiencia . 
	Dado un Elfo y enemigos o fantasmas genéricos
	Cuando el Elfo derrote enemigos o fantasmas genéricos
	Entonces el Elfo incrementará su experiencia de acuerdo al nivel de los derrotados.

> Como Personaje, Quiero acumular experiencia 
> Resultado: Para poder subir de nivel

*Criterios de aceptación:*  
	
1.	Titulo: Subir nivel  
	Dado un personaje y muchos enemigos
	Cuando el personaje derrote muchos enemigos
	Entonces el personaje incrementará su experiencia y podrá ir superando los límites de puntos para subir el nivel. 

> Como Personaje, Quiero subir de nivel 
> Resultado: Para poder asignar puntos adicionales a mis habilidades

*Criterios de aceptación:*  
	
1.	Titulo: Puntos adicionales
	Dado un personaje
	Cuando el personaje sube de nivel
	Entonces obtendrá puntos adicionales para sus habilidades.

2.	Titulo: Mejorar habilidades
	Dado un personaje
	Cuando el personaje mejora sus habilidades
	Entonces el personaje podrá asignar los puntos adicionales a sus habilidades para mejorarlas.    
  
3.	Titulo: Mejorar manejo de Items
	Dado un personaje
	Cuando el personaje se equipa con Items
	Entonces el personaje mejorará sus atributos para tener mejor desempeño en los combates.   
    
4.	Titulo: Agregar Habilidades
	Dado un personaje
	Cuando el personaje añada una habilidad
	Entonces el personaje tendrá una nueva habilidad que afecte sus atributos.
    
> Como Personaje, Quiero aumentar mis habilidades 
> Resultado: Para poder manipular items de manera mas eficiente

*Criterios de aceptación:*  
	
1.	Titulo: Mejorar atributos
	Dado un personaje
	Cuando el personaje aumenta sus habilidades e incrementa el valor de ciertos atributos
	Entonces el personaje utilizará los ítems de manera mas eficiente.    
    
> Como Personaje, Quiero equipar items 
> Resultado: Para poder potenciar mis habilidades

*Criterios de aceptación:*  
	
1.	Titulo: Obtener Item
	Dado un personaje y su enemigo
	Cuando el personaje mata a su enemigo
	Entonces el personaje se equipará con el mejor ítem.      

/*Repetido*/
2.	Titulo: Obtener Item
	Dado un personaje
	Cuando el personaje encuentra un item
	Entonces el personaje se equipará con un item que encuentre en el mapa.        
/*Repetido(Mas arriba se explica algo parecido)*/   
3.	Titulo: Atributos mejorados
	Dado un personaje
	Cuando el personaje selecciona un ítem nuevo
	Entonces el personaje será mas fuerte con la combinación de item y habilidad.

> Como Personaje, Quiero disponer de habilidades de destreza, fuerza e inteligencia.
> Resultado: Para afectar a mis puntos de ataque, magia y defensa.

*Criterios de aceptación:*  
	
1.	Titulo: Destreza
	Dado un personaje
	Cuando el personaje agrega a su lista de habilidades la Destreza
	Entonces el personaje aumentará su velocidad y potencia.         
    
2.	Titulo: Fuerza
	Dado un personaje
	Cuando el personaje agrega a su lista de habilidades la Fuerza
	Entonces el personaje aumentará los puntos de ataque y potencia.        
        
3.	Titulo: Inteligencia
	Dado un personaje
	Cuando el personaje agrega a su lista de habilidades la Inteligencia
	Entonces el personaje aumentará la magia (en caso de tenerla) y la defensa.

4.	Titulo: Velocidad
	Dado un personaje
	Cuando el personaje agrega a su lista de habilidades la Velocidad
	Entonces el personaje aumentará el ataque y la velocidad.

5.	Titulo: Evasion
	Dado un personaje
	Cuando el personaje agrega a su lista de habilidades la Evasion
	Entonces el personaje aumentará la defensa.

6.	Titulo: Valentia
	Dado un personaje
	Cuando el personaje agrega a su lista de habilidades la Valentia
	Entonces el personaje aumentará el ataque.

> Como Personaje, Quiero encontrarme con otros personajes en el mismo mundo.
> Resultado: Para aliarse a ellos o combatir contra ellos.

*Criterios de aceptación:*  
	
1.	Titulo: Alianzas
	Dados dos o más personajes
	Cuando los personajes e encuentran cerca y deciden unirse
	Entonces los personajes formarán una nueva alianza para combatir contra sus enemigos.
    
2.	Titulo: Combate
	Dados dos o más personajes
	Cuando los personajes e encuentran cerca y deciden atacarse
	Entonces los personajes y sus alianzas combatirán hasta definir un ganador.

> Como Personaje, Quiero aliarme con otro personaje.
> Resultado: Para combatir junto a él y aumentar la experiencia que recolectamos en ese tiempo.

*Criterios de aceptación:*

1.	Titulo: Combate junto a miembros
	Dadas dos alianzas
	Cuando ambas deciden combatir
	Entonces se incrementará la experiencia de la alianza ganadora.

/*Repetido(Se podria juntar todo)*/   
2.	Titulo: Alianza contra Personaje
	Dada una alianza y un personaje
	Cuando la alianza y el personaje deciden combatir
	Entonces el ganador incrementará su experiencia.

	/*Repetido*/	
3.	Titulo: Alianza contra PersonajeGenerico
	Dada una alianza y un personaje genérico
	Cuando la alianza combate contra el personaje generico
	Entonces el ganador incrementará su experiencia.

> Como Personaje, Quiero combatir contra otros jugadores.
> Resultado: Para obtener sus items al derrotarlos.

*Criterios de aceptación:*

1.	Titulo: Alianza ganadora
	Dadas dos alianzas
	Cuando dos alianzas finalizan el combate y existe un ganador.
	Entonces la alianza ganadora se repartirá los items de la alianza perdedora.
    
2.	Titulo: Item de Personaje
	Dados dos personajes
	Cuando dos personajes finalizan el combate y existe un ganador
	Entonces el personaje ganador recibirá el mejor item del personaje perdedor. 
        
3.	Titulo: Item de Genérico
	Dado un personaje y un personaje genérico
	Cuando el personaje derrota al personaje genérico
	Entonces el personaje recibirá el item del personaje genérico.

> Como Personaje,Quiero cambiar las alianzas establecidas cada cierta cantidad de tiempo.
> Resultado: Para poder traicionar a mis aliados.

*Criterios de aceptación:*

1.	Titulo: Salir de la alianza
	Dado un jugador y sui alianza
	Cuando se cumple el tiempo limite minimo para salir de la alianza y el jugador decide salir
	Entonces el jugador dejará de formar parte de la alianza.

> Como Personaje, puedo morir en combate.
> Resultado: Reaparecer en una zona segura.

*Criterios de aceptación:*

1.	Titulo: Reaparecer
	Dado un personaje
	Cuando el personaje muere en combate
	Entonces el personaje reaparecerá en un lugar seguro.

> Como Personaje, Quiero agregar ataques.
> Resultado: Para poder hacer mas daño.

*Criterios de aceptación:*

1.	Titulo: Nuevo Ataque
	Dado un personaje
	Cuando el personaje adquiere un determinado nivel
	Entonces el personaje obtendrá un nuevo ataque.

> Como Personaje, Quiero dirigirme al lugar seguro.
> Resultado: Para recuperar salud.

*Criterios de aceptación:*

1.	Titulo: Sanar Personaje
	Dado un pesonaje
	Cuando el personaje con poca salud desea recuperarse y entra a la zona segura
	Entonces el personaje recuperará su salud, pudiendo o no, llegar la misma al máximo.