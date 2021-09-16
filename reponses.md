# MODEL - TD TDD

## Objectifs

Lien Github du TD = https://github.com/PaulGorlicki/test

Dans ce TD nous allons illustrer le principe du TDD sur un exemple simple. Nous allons aussi illustrer l'utilisation de stubs et de couverture du code par les tests. Vous pourrez utiliser l'IDE de votre choix **mais** l'ensemble du travail doit pouvoir fonctionner hors IDE à l'aide du système de build indiqué dans la suite.

## Installation

1. créer un projet gradle et le versionner sur GitHub. Remplacer le fichier `build.gradle` par celui fourni en ligne (pensez à modifier ce qui serait spécifique à votre projet).

2. dans ce projet nous allons utiliser JUnit, Hamcrest et Mockito.

        **A quoi servent ces librairies ?**
        Ce sont des frameworks qui servent à écrire des tests logiciels en Java.

3. vérifier que tout marche bien avec `./gradlew cleanTest test`

## Sujet

Au cours de ce TD, nous allons procéder de la manière suivante. Nous allons tenir à jour une *task list* contenant une liste de tâches à réaliser vis-à-vis du code. A chaque étape, nous choisirons dans cette liste vers quelle étape procéder, tout en rajoutant éventuellement d’autres tâches sur cette liste. Cette liste de tâche sera un fichier texte organisé comme suit :

```
[X] 1- description de la tâche
[X] 2- description de la tâche
[ ] 3- description de la tâche
...
```

Le sujet de ce TD porte sur la réalisation d’un dictionnaire bilingue. Stricto sensu, il s’agit de pouvoir réaliser des traductions entre deux langues, dans les deux sens. Pour des raisons de portabilité, il sera intéressant de pouvoir sauvegarder et charger des dictionnaires à travers des fichiers externes.

**Construire une liste de tâche associée au TD**

## Fake it !

La question du choix de la première tâche à réaliser est une question importante. Elle doit être suffisamment simple pour permettre de réaliser un premier cycle *red-green-refactor* rapidement.Manifestement, dans notre problème, la classe centrale est la classe `Dictionary`.

**Commençons par écrire un test qui ne passe pas pour cette classe.**

1. Construire une classe de tests nommée `DictionaryTest`.
2. Ecrire dans cette classe un test créant un objet de type `Dictionary`, lui assignant un nom, et vérifiant que ce nom est correctement stocké dans l’objet.

       Il est fondamental d’écrire un test qui ne passe pas, comme par exemple :
       
       ```java
   @Test public void testDictionaryName() {
        assertThat(dict.getName(), equalTo("Example"));
        }

        ```
        ```
    
        En ce sens, rien n’est imposé par rapport au choix des noms des classes ou des méthodes, puisque la classe `Dictionary` n’existe pas encore.

3. Maintenant que la *red bar* est atteinte, nous allons tâcher de faire passer le test. Pour cela, il existe plusieurs techniques. Celle utilisée ici est nommée *Fake it* (litt. « Fais semblant »). Concrètement, elle consiste à faire le minimum nécessaire pour faire passer le test. Dans notre cas, il suffit d’une méthode `getName()` renvoyant la chaîne de caractères `"Example"`.

        Créer une classe `Dictionary` vide, puis lui ajouter un constructeur vide.

4. Écrire une méthode vide `getName()` renvoyant la chaîne de caractères `"Example"`.

        La notion de « semblant » est ici aussi fondamentale : elle permet de construire le code pas à pas, en utilisant à chaque étape une méthode simple, rapide, et faisant passer les tests existants.
        
        Cette pratique autorise un grand nombre de dérives dans le style de programmation : variables globales ou publiques, conversions du type (cast) des objets ... dont il faudra tenir compte lors de la phase de refactoring.

5. Lorsque la *green bar* est atteinte arrive la phase la plus complexe du TDD : la phase de *refactoring*. Pour l’instant, nous allons nous limiter à éliminer les duplications de code.

        Quelle duplication existe pour l’instant dans notre code ?
        Les classes Dictionary et DictionaryTest ainsi que leur méthode.

6. Supprimer la duplication du code en introduisant un attribut privé `name`, et adapter le constructeur et la méthode `getName()`de manière à s’assurer que cette variable soit correctement positionnée et renvoyée.

        Qu’englobe la notion de refactoring ? Toute forme de modification du code qui conserve le passage des tests existants, et qui permet d’obtenir une architecture logicielle avec un minimum de défauts.
        Quelques exemples :
        
        – supprimer la duplication du code / déplacer du code ;
        
        – ajuster le caractère privé/public des attributs/méthodes.

7. Le cycle de travail est maintenant bouclé. Il devient alors possible de recommencer ce cycle avec un nouveau test. Les tests pré-existant assurent une certaine confiance dans le code déjà écrit, et permettent d’envisager les modifications futures avec sérénité.

        Utiliser la technique précédente pour écrire un test, puis une méthode permettant de vérifier si un dictionnaire est vide ou pas (méthode `isEmpty`). En l’absence de méthodes pour ajouter quoi que ce soit au dictionnaire, on se limitera à renvoyer une valeur constante.

8. Comme cette fonctionnalité n’est pas implémentée de manière correcte, rajouter le problème du traitement du dictionnaire vide dans la task list.

## Triangulation

Le TDD insiste profondément sur la programmation par nécessité. Il faut d’abord écrire le test qui génère un besoin fonctionnel (*test-first*), avant de coder ce besoin. Néanmoins, la méthode *Fake it* vue précédemment montre qu’il est possible de faire passer des tests à un programme sans réellement écrire le code nécessaire. Le problème vient ici du fait que nous n’avons pas suffisamment spécifié les tests permettant de cerner le comportement d’une méthode. Pour raffiner les tests, nous allons appliquer la méthode de triangulation.

1. Écrire un test permettant de vérifier que l’ajout d’une traduction au dictionnaire (`addTranslation`) se passe correctement lors de la vérification (`getTranslation`).

        ```java
        @Test public void testOneTranslation() {
                dict.addTranslation("contre", "against");
                assertThat(dict.getTranslation("contre"), equalsTo("against"));
        }
        ```

2. Est-il possible de faire un test qui n’implique l’ajout que d’une seule de ces deux méthodes ?

3. Utiliser *Fake it* pour faire passer le test en faisant renvoyer à `getTranslation` la réponse attendue par le test.

4. Ici, notre test n’est pas suffisamment précis, et l’implémentation obtenue est correcte d’un point de vue des tests. Trianguler consiste ici à raffiner le test pour mieux cibler le comportement du code.

        Ajouter dans le test la vérification d’une seconde traduction qui soit différente de la première.

5. Maintenant, il faut faire un choix : soit se limiter à une implémentation-simulacre, soit ajouter un morceau de code capable d’effectivement gérer les traductions. C’est la deuxième solution que l’on choisit maintenant.

        Ajouter à la classe `Dictionary`une table de hachage `Map<String, String> translations`.

6. Rendre le code de `addTranslation` et de `getTranslation` correct.

7. Vu que l’on dispose à présent d’un moyen correct pour remplir le dictionnaire avec des traductions,
   il devient possible de s’occuper du cas du test du dictionnaire vide.

        Améliorer le test du vide du dictionnaire en augmentant le test initial.

## Les fixtures de JUnit

L’écriture de tests dans la classe `Dictionary` fait souvent preuve de redondance. Il est possible de factoriser l’initialisation des tests que l’on réalise avec JUnit, et cela en utilisant encore les annotations Java :

```java
@Before public void initialize () {
        dict = new Dictionary("Example");
}
```

Une méthode annotée avec `@Before` sera exécutée avant chaque test, et une méthode annotée avec `@After` à la fin de chaque test. Ces deux fonctionnalités permettent de mettre en place une installation (fixture) commune à tous les tests.

**Prévoir une fixture pour l’ensemble des tests de la classe DictionaryTest.**

## Traductions multiples

TBD

## Traduction inverse

TBD

## Chargement de fichier

TBD

## Les test suites de JUnit

A partir de maintenant, nous avons plusieurs classes à gérer. Pour permettre à JUnit de traiter les tests de manière uniforme, il est pratique d’écrire un ensemble de tests, sous la forme d’une test suite :

```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
    DictionaryTest.class,
})
public class AllTests { // Empty class ( introspection )
}
```

Pour lancer les tests associés à une suite de tests, utiliser :

```sh
./gradlew cleanTest test --tests nom.du.package.AllTests
```

**Ecrivez cette suite de test et vérifiez que cela fonctionne.**

## Free wheeling

TBD

## Couverture

1. JaCoCo (Java Code Coverage) et l'un des moyens de calculer la couverture du code dans les tests.

        **Typiquement, que peut-on espérer concernant le taux de couverture atteint-on  en utilisant la technique du TDD ?**

2. Lancer le calcul des informations de couverture en rajoutant la tâche `jacocoTestReport` au lancement de `./gradlew`. Observez les résultats (ouvrir la page d'index HTML générée par JaCoCo). **Complétez au besoin les tests de `Dictionary` et `DictionaryParser`.**

## Conclusion

**Discuter des avantages et des inconvénients de la technique du TDD par rapport à vos techniques de développement usuelles.**