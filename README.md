Membres du projet : Melvin Beaussart, Alexis Salvetti, Arthur Marzinkowski, Pellois Mathilde, Duterte Sébastien

Les difficultés rencontrées qui n'ont pas été résolues : La révision de code avec SonarLint nous as posé des problèmes, nous 
avons donc corrigés les erreurs que l'on pouvait corriger et nous avons essayé de maintenir une programmation la plus objet 
possible.

Autres difficultés rencontrées mais résolues : 
Nous avons perdu beaucoup de temps sur l'installation de javaFx, d'ailleurs nous avons dû utiliser les consignes
d'Arthur pour pouvoir l'installer correctement. Nous avons chacun essayé le tutoriel dans le TP sur javaFx et 
nous avons eut des problèmes.
La partie sur les niveaux des IA a pris aussi beaucoup de temps (nous avons changés plusieurs fois la façon de programmer
l'ordinateur avec le plus haut niveau de difficulté, les erreurs étaient subtiles et difficiles à repérer et cela a fait
perdre pas mal de temps).

Fonctionnalités implémantées : Nous avons implémenté l'ensemble des fonctionnalités demandées concernant le fonctionnement
du programme, en effet, le jeu permet de jouer à des parties de labyrinthe avec ou sans ordinateurs, avec plusieurs niveaux
de difficultés pour ces derniers. De plus on peut choisir entre 3 variantes, la première est la variante de base sans trésors
vivants, la deuxième inclus les déplacements de trésors vivants, et la troisième inclus les déplacements et les actions
de trésors vivants. Il est possible de choisir entre le thème basique et le thème océan (nous avons choisi d'en faire 2 
histoire de vous montrer que nous savons comment procéder).

Concernant les IA : 
L'IA possédant le premier niveau de difficulté va choisir l'endroit de l'insertion dans le plateau ainsi que la rotation de la
carte qu'elle insère de manière aléatoire, ensuite elle va se déplacer vers le point accessible le plus proche possible du trésor
ou du point de départ si elle a récupéré tous les trésors.


L'IA possédant le deuxième niveau de difficulté va récupérer les points qu'elle peut atteindre et qui se rapprochent le plus 
possible du trésor pour chaque insertion possible dans le plateau ainsi que pour chaque rotation de carte.
Ensuite elle va en déduire la rotation de carte ainsi que l'insertion la plus avantageuse possible (celle qui 
correspond au point dont la distance avec le trésor (ou le point de départ si elle a trouvée tous les trésors) 
est la plus petite possible).
Elle se déplace ensuite vers le point trouvé.
Ce niveau de difficulté fait gagner l'IA en moins de 10 tours dans la plupart des cas.

Concernant les trésors vivants :
Ils ont une probabilité de 70% de se déplacer et si la variante avec les pénalité est activée, ils ont 1/3 de soit ne rien faire,
soit donner un bonus, soit de donner un malus à tous les joueurs sur leur carte.

Répartition du travail:
La répartition du travail était tout à fait équitable, mais la répartition des commits n'est pas identique, cela s'explique
par le fait que nous ne travaillons pas chacun de notre côté, en effet il nous arrivait souvent d'effectuer des partages 
d'écrans sur discord pour pouvoir travailler ensemble sur un même ordinateur, cela permet d'aller beaucoup plus vite sur 
la réalisation de certaines parties, et permet de repérer les éventuelles erreurs de programmation avant même de tester le
programme. C'est pour cette raison que nous ne pensons pas qu'une notation basée sur les commits est efficace pour
juger de l'implication de chaque membre du groupe dans le projet.

Concernant les parties de chacun : 
-Melvin s'est occupé des classes PlayerAI, du modèle (classe Game) et BoardGame et des trésors vivants
-Alexis s'est occupé de la classe Card et de la CardInterface, des insertions dans plateau    
-Mathilde s'est occupée des trésors et de toutes les images du jeu ainsi que les thèmes et de la forme de l'interface graphique
-Alexis et Arthur se sont occupés de la Vue et des Controlleurs
-Sébastien s'est occupé du Player, de la PlayerInterface et du modèle (classe Game) ainsi que du diagramme de classes, du Builder et des Factory.
Nous avons tous participé à la documentation.

Fonctionnement de l'application:
Une première fenêtre représentant une partie s'ouvre, il y est possible uniquement de commencer une nouvelle partie ou de 
quitter le jeu.
Lorsque l'on fait une nouvelle partie, le jeu nous propose de choisir le nombre d'ordinateurs et de joueurs réels, la difficulté
des ordinateurs, la variante, et le thème du jeu.
Le jeu ensuite se met à jour et indique le tour du joueur avec l'image de son pion en haut à gauche, notons que comme l'attribution
du joueur qui commence est aléatoire, des ordinateurs ont peut-être déjà joué.
Il faut cliquer sur un des boutons d'insertion pour insérer une carte (on choisi avant l'orientation de la carte entrante en
la faisant pivoter avec les 2 boutons à droite); pour éviter l'annulation du coup du joueur précédent, le bouton d'insertion
annulant le coup n'est pas cliquable.  Ensuite il est possible de déplacer son pion de carte en carte si cela est 
possible et on peut mettre fin à son tour (notons que si le joueur se déplace sur le trésor qu'il doit chercher, le tour
est automatiquement terminé) ensuite le jeu verifie si le prochain joueur est un ordinateur, si tel est le cas il le fait jouer
(cela se répète tant que c'est à un ordinateur de jouer).
Lorsqu'un joueur n'a plus de trésor à récupérer et qu'il regagne son point de départ, tous les boutons sauf les boutons 
nouvelle partie et quitter se désactivent, et on indique que le joueur a gagné en bas de la fenêtre.
A la fin de chaque tour (d'un ordinateur ou d'un joueur réel), si la variante avec les trésors vivants est activée, ceux-ci se
déplacent et si la variante avec les pénalités est activée, alors ils donnent une pénalité aux joueurs sur leurs cartes.

Diagramme de classe : Voir sur le gitlab, ClassDiagram.png dans le dossier diagrammes

execution : nous avons suivi le tutoriel du tp sur la création de jar
voici la commande pour executer le programme : ./runfx run
