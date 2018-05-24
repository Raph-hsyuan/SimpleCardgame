## Dernière Mise à Jour:
- **Séparer le lancement de programme avec la statistique de taux de victoires**
- **Lancement de jeu**
	- `<mainClass>cpa.inventeur.Main</mainClass> `
- **Statistique de taux de victoires**
 	- `<mainClass>cpa.inventeur.Statistics</mainClass>` 
- **Corriger une faute de programmation :**
	- Decomposer `toPlay()` dans le ***RobotNormal*** pour faire les tests unitaires
	- Changer ***Robot*** interface -> abstract class
	- Ajouter sous-package
	- Faire test unitaire pour RobotNormal

## Détail de chaque version 
### [1- Jeu très simple](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11120) 
> Un bot qui joue un inventeur sur une invention. L'invention est terminée.le robot obtient un point, Le jeu s'arrête
### [2- Jeu plusieurs tours](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11125) 
> Un bot qui a deux inventeurs, dans le GameBoard il y a 4 inventions a faire, si l'inventeur est occupe, il faut le rendre disponible, Si tous les inventions sont terminées. Le jeu s'arrête
### [3- Jeu avec les competence](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11129) 
> Ajouter 4 competences aux inventeurs, 4 inventions demandent certain nombre de inventeur qui a certain competance
### [4- Jeu avec plusieur players](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11133) 
> On peut choisir le nombre(1-5) et le type (Simple ou Normal) de robot qui participe au jeu, enfin qui obtient le plus haut score va gagner le jeu, pour qu'il fonctionne bien, il faut ajouter tous les inventors a enum, en plus il faut reamenager les inventions
### [5- Jeu avec 2 bonus](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11130) 
> Si un player a finit une invention, il peut obtient un bonus qui est defini sur l'invention, un ticket pour gagner le point supplementaire directement ou un ticket pour rendre tous les inventeur disponible tout de suite, chaque invention a 2~3 bonus, on compter le contributions de chaque robot, ils peuvent choisir un par un (en ordre decroissant)mais seulement une fois jusqu'a tous les contribuants ont choisit, enfin qui obtient le plus haut score va gagner le jeu, bien sur, RobotNormal sait bien comment utilise les tickets, mais RobotSimple depense les tickets tout a suite, s'il en a un.
### [6- Jeu avec invention numerote](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11131) (À venir) 
> Les Inventions ont des Points differents. en plus Sur chaque invention figure un chiffre (le numéro de classification du brevet). En fin de partie, les joueurs marquent un nombre de points de victoire égal au chiffre bleu le plus élevé de chacune de leurs suites. Une suite n’est valable que si elle commence par 0.
### [7- Jeu avec 5 bonus](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11132) (À venir)
> Ajouter 3 types de bonus, un ticket pour remplacer n'import quel numero et un ticket pour renforcer une capacite d'inventeur, un ticket qui peut remplacer seulement une fois deux points de une competence
### [8- Jeu avec plusieurs inventions](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11139) (À venir)
> Ajouter 24 inventions differents (8 inventions pour une periode, les inventions sont plus en plus difficiles), a la fin de chaque periode, il va clear la table, et mettre les nouvelles inventions sur table
### [9- Jeu avec les missions](https://mjollnir.unice.fr/jira/browse/CPA/fixforversion/11140) (À venir)
> Chaque inventeur a une mission,( un but du renforcement de competence) s'il accomplit une tache il va obtenir les points supplementaire d'apres la degre de difficulte de mission

## DEMO du Jeu:
> 
	<mainClass>cpa.inventeur.Main</mainClass> 
###Round Start 
>
- Print round number
- Print hands Before Round
	- ![](https://i.imgur.com/rLJPz6H.png)
- Print Table Before Round
	- ![](https://i.imgur.com/GiW3IKT.png)
- Print What have players done
	- ![](https://i.imgur.com/iAsQ7G4.png)
- Print A Round Finish
	- ![](https://i.imgur.com/0LkxXY1.png)
- Print hands After Round
	- ![](https://i.imgur.com/Svjlj9o.png)
- Print Table After Round
	- ![](https://i.imgur.com/bGOkK8n.png)
- Print Score Board
	- ![](https://i.imgur.com/qHvRWyl.png)
- Start Another Round until no Invention Remain
####  Game Finish 
> 
- Print Game Finish
- Print Score Board
- Print Who is Winner
- Print Round Total
- Print Number of Invention remain
- ![](https://i.imgur.com/PJGdrJS.png)
#### Other Detail 
> 
- Print if Someone finish a invention
- Print who choose Ticket
- Print choose what Ticket
- ![](https://i.imgur.com/KcXV8GW.png)
- ![](https://i.imgur.com/AH5EtP3.png)

##Statistic Du Jeu
> 
    <mainClass>cpa.inventeur.Statistics</mainClass>
-  Faire statistique de taux de victoires
	-  Un RobotNormal vs Un RobotSimple
	-  Un RobotNormal vs Deux RobotSimple
	-  Un RobotNormal vs Trois RobotSimple
	-  Un RobotNormal vs Quatre RobotSimple
- ![](https://i.imgur.com/APFcuV0.png)
## Class Principaux: 
- ###***Table.class :***
	- Présenter les inventions valide dans le jeu
	- Présenter les opération valides d’inventions  
- ###***PlayerConsole.class :***
	- Comme l’interface entre Joueur et Jeu.
	- Présente tous les actions valides.
	- Limiter le nombre d’opération de joueur dans un round.
	- Détecter les événements comme Ajouter Point ou Finir qqch et les annoncent à GameEngine. 
	- Avoir une liste d’inventeurs qui est initialisé selon le PlayerColor, des inventeurs de certain PlayerColor sont fixes.
- ###***GameEngine.class :***
	- Créer les PlayerConsole de couleur diffèrent d’après le nombre de joueur.
	- Ajouter les inventions random à la Table d’après le nombre de joueur.
	- Ajouter les Tickets (Bonus) random aux inventions.
	- Établir la liaison entre joueur(Robot) et PlayerConsole
	- Possède et opère le Tableau de Score d’après l’annonce de PlayerConsole
	- Distribuer les Tickets(Bonus) d’après l’annonce de PlayerConsole et l’information (taille de la contribution) présenté par certaine invention.
	- Régler l’ordre de round et jeu.

## Détail Du Jeu: 
- ###**Joueur :** 
	- 1 à 5 (cinq couleurs maximal)
- ###**Invention :** 
	- 12 (1er époque)
- ###**Inventeur :** 
	- 4*5=20 (quatre inventeurs pour un couleur)**
- ###**Compétence :** 
	- 4 (Skill)
- ###**Ticket (Bonus):** 
	- ADD A POINT
	- SET ALL FREE
- ###**Robot: 2 types:** 
	- Simple
	- Normal 
- ###**Ordre de Distribution de Bonus :** 
	- Selon le taille de la contribution de chaque contribuant. (Ordre décroissant)**
- ###**Fin de jeu :** ###
	- Aucune invention dans la Table.
- ###**Gagner le point :** 
	- Finir une invention +1
	- Use Ticket ADD ONE POINT +1
- ###**Gagner le jeu :** 
	- Avoir le meilleur score



    

