# API-LaReleve

## Continuous integration
**Master :**
[![Build Status](https://travis-ci.org/ECOM-LaReleve/API-LaReleve.svg?branch=master)](https://travis-ci.org/ECOM-LaReleve/API-LaReleve)

**Develop :**
[![Build Status](https://travis-ci.org/ECOM-LaReleve/API-LaReleve.svg?branch=develop)](https://travis-ci.org/ECOM-LaReleve/API-LaReleve)

## Docker
[gattazr/lareleve](https://hub.docker.com/r/gattazr/lareleve/)


## Routes


**GET**


Find all :
```javascript 
/poles  
/services  
/besoins  
/prestations  
/actes
/prestationsrealisees
/actesrealisees
/logements
/langues
/nationnalites
/ressources
/languesindividus
/nationnalitesindividus
/ressourcesindividus
/ressourcesmenages
/utilisateurs
/individus
/menages
```


Find by id :
```javascript
/poles/{id}
/services/{id}
/besoins/{id}
/prestations/{id}
/actes/{id}
/prestationsrealisees/{id}
/actesrealisees/{id}
/logements/{id}
/langues/{id}
/nationnalites/{id}
/ressources/{id}
/languesindividus/{id}
/nationnalitesindividus/{id}
/ressourcesindividus/{id}
/ressourcesmenages/{id}
/utilisateurs/{id}
/individus/{id}
/menages/{id}
```


Find prestations realisees by id utilisateur :
```javascript
/prestationsrealisees?idutilisateur={id}
```

Find prestations realisees by id individu :
```javascript
/prestationsrealisees?idindividu={id}
```

Find prestations realisees by id menage :
```javascript
/prestationsrealisees?idmenage={id}
```

Find prestations realisees by id prestation :
```javascript
/prestationsrealisees?idprestation={id}
```


Find actes realises by id utilisateur :
```javascript
/actesrealisees?idutilisateur={id}
```

Find actes realises by id individu :
```javascript
/actesrealisees?idindividu={id}
```

Find actes realises by id menage :
```javascript
/actesrealisees?idmenage={id}
```

Find actes realises by id acte :
```javascript
/actesrealisees?idacte={id}
```

Find actes realises by id besoin :
```javascript
/actesrealisees?idbesoin={id}
```

Find actes realises by id prestation :
```javascript
/actesrealisees?idprestation={id}
```


Find menage by id referent :
```javascript
/menages?idreferant={id}
```

Find menage by individu name :
```javascript
/individus?menage={nomUsage}
```

Find individu by id menage :
```javascript
/individus?idmenage={id}
```


Find langues individus by id langue :
```javascript
/languesindividus?idlangue={id}
```

Find langues individus by id individu :
```javascript
/languesindividus?idindividu={id}
```

Find nationnalites individus by id nationnalite :
```javascript
/nationnalitesindividus?idnationnalite={id}
```

Find nationnalites individus by id individu :
```javascript
/nationnalitesindividus?idindividu={id}
```

Find ressource individus by id individu :
```javascript
/ressourcesindividus?idindividu={id}
```

Find ressource individus by id ressource :
```javascript
/ressourcesindividus?idressource={id}
```

Find ressource menages by id menage :
```javascript
/ressourcesmenages?idmenage={id}
```

Find ressource menages by id ressource :
```javascript
/ressourcesmenages?idressource={id}
```


**POST**

```javascript
/poles
/services
/besoins
/prestations
/actes
/prestationsrealisees
/actesrealisees
/logements
/langues
/nationnalites
/ressources
/languesindividus
/nationnalitesindividus
/ressourcesindividus
/ressourcesmenages
/utilisateurs
/individus
/menages
```


**PUT**

```javascript
/poles/{id}
/services/{id}
/besoins/{id}
/prestations/{id}
/actes/{id}
/prestationsrealisees/{id}
/actesrealisees/{id}
/logements/{id}
/langues/{id}
/nationnalites/{id}
/ressources/{id}
/languesindividus/{id}
/nationnalitesindividus/{id}
/ressourcesindividus/{id}
/ressourcesmenages/{id}
/utilisateurs/{id}
/individus/{id}
/menages/{id}
```