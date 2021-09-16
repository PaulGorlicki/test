# MODEL - TD TDD

## 

Lien Github du TD = https://github.com/PaulGorlicki/test

1. dans ce projet nous allons utiliser JUnit, Hamcrest et Mockito.

        **A quoi servent ces librairies ?**
        Ce sont des frameworks qui servent à écrire des tests logiciels en Java.


5.     Quelle duplication existe pour l’instant dans notre code ?
        On a une duplication du getName() et du constructeur.
    

## 

1. Est-il possible de faire un test qui n’implique l’ajout que d’une seule de ces deux méthodes ?

    Oui, on peut vérifier chaque méthode. Les deux marchent indépendamment de l'autre. 









1. Maintenant, il faut faire un choix : soit se limiter à une implémentation-simulacre, soit ajouter un morceau de code capable d’effectivement gérer les traductions. C’est la deuxième solution que l’on choisit maintenant.

        Ajouter à la classe `Dictionary`une table de hachage `Map<String, String> translations`.

2. Rendre le code de `addTranslation` et de `getTranslation` correct.

3. Vu que l’on dispose à présent d’un moyen correct pour remplir le dictionnaire avec des traductions,
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