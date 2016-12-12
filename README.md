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


FIND ALL :
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


FIND BY ID :
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


FIND PRESTATIONS REALISEES BY ID UTILISATEUR :
```javascript
/prestationsrealisees?idutilisateur={id}
```

FIND PRESTATIONS REALISEES BY ID INDIVIDU :
```javascript
/prestationsrealisees?idindividu={id}
```

FIND PRESTATIONS REALISEES BY ID MENAGE :
```javascript
/prestationsrealisees?idmenage={id}
```

FIND PRESTATIONS REALISEES BY ID PRESTATION :
```javascript
/prestationsrealisees?idprestation={id}
```


FIND ACTES REALISES BY ID UTILISATEUR :
```javascript
/actesrealisees?idutilisateur={id}
```

FIND ACTES REALISES BY ID INDIVIDU :
```javascript
/actesrealisees?idindividu={id}
```

FIND ACTES REALISES BY ID MENAGE :
```javascript
/actesrealisees?idmenage={id}
```

FIND ACTES REALISES BY ID ACTE :
```javascript
/actesrealisees?idacte={id}
```

FIND ACTES REALISES BY ID BESOIN :
```javascript
/actesrealisees?idbesoin={id}
```

FIND ACTES REALISES BY ID PRESTATION :
```javascript
/actesrealisees?idprestation={id}
```


FIND MENAGE BY ID REFERENT:
```javascript
/menages?idreferant={id}
```

FIND MENAGE BY INDIVIDU NAME:
```javascript
/individus?menage={nomUsage}
```

FIND INDIVIDUS BY ID MENAGE:
```javascript
/individus?idmenage={id}
```


FIND LANGUES INDIVIDUS BY ID LANGUE :
```javascript
/languesindividus?idlangue={id}
```

FIND LANGUES INDIVIDUS BY ID INDIVIDU :
```javascript
/languesindividus?idindividu={id}
```

FIND NATIONNALITES INDIVIDUS BY ID NATIONNALITE :
```javascript
/nationnalitesindividus?idnationnalite={id}
```

FIND NATIONNALITES INDIVIDUS BY ID INDIVIDU :
```javascript
/nationnalitesindividus?idindividu={id}
```

FIND RESSOURCES INDIVIDUS BY ID INDIVIDU :
```javascript
/ressourcesindividus?idindividu={id}
```

FIND RESSOURCES INDIVIDUS BY ID RESSOURCE :
```javascript
/ressourcesindividus?idressource={id}
```

FIND RESSOURCES MENAGES BY ID MENAGE :
```javascript
/ressourcesmenages?idmenage={id}
```

FIND RESSOURCES MENAGES BY ID RESSOURCE :
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