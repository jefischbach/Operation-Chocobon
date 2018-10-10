# Operation-Chocobon
TP d'Intelligence Artificielle - Implantation du TD sous Java avec la lib Choco

## Liens importants:
[Sujet de TD](https://ent.uca.fr/moodle/pluginfile.php/327664/mod_resource/content/1/TD1_IA.pdf)

[Corrections de TD](https://perso.liris.cnrs.fr/christine.solnon/Site-PPC/session2/e-miage-ppc-sess2.htm#exo_1) (les liens des corrections sont en fin de chaque section)

[Cours sur le CSP](https://perso.liris.cnrs.fr/christine.solnon/Site-PPC/session1/e-miage-ppc-sess1.htm#grand_2)

[Choco](http://www.choco-solver.org/)
- Téléchargement sous le logo
- Javadoc et guide un tout petit peu plus bas

[Netbeans](https://netbeans.org/downloads/index.html) (Java SE devrait largement suffire) (bien pensé à avoir le JDK installé (vérifie avec `java -version` normalement ça devrait aller :p))

## Netbeans

Faire un nouveau projet:  `File -> New Project -> Java | Java Application`

Par souci que j'ai la flemme d'utiliser git correctement (et de faire des phrases jolies) j'upload uniquement les fichier sources .java, qui se trouvent normalement dans `~/NetBeansProjects/Exo1Monnaie/src/exo1monnaie` (par exemple)

Importer la lib choco: Ouvre le menu déroulant du projet (à gauche) en cliquant sur le triangle, clic droit sur Libraries et Add Library. Bien penser à ajouter choco-solver-4.0.8.jar **et** choco-solver-4.0.8-sources.jar (qui permet d'avoir accès à la javadoc quand tu codes).

Petits tips qui vont bien:
- Si la ligne est en rouge, `ALt+Enter` pour faire apparaître des options de correction automatique (genre des import, ça gagne du temps)
- Si tu commences à écrire une classe puis un point (genre model.) Netbeans affiche la liste des méthodes et attributs de la classe et leurs détails (d'où l'intérêt de la javadoc).
- De la même manière, si tu cliques sur une méthode (ou autre chose, j'ai pas test mais c'est logique), `Ctrl+Espace` pour en afficher la javadoc.

## Choco

Classe Model: obligé de l'instancier pour chaque programme. Sorte de conteneur essentiel qui contient les variables, les contraintes, les objectifs (min, max...)... 

Bien se souvenir que pour les contraintes, on place une contrainte sur une vue d'une variable (avec intScaleView dans le cas d'une multiplication par exemple), et non la variable elle-même.
