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


FIND BY ID : 
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


FIND PRESTATIONS REALISEES BY ID UTILISATEUR :
/prestationsrealisees?idutilisateur={id}

FIND PRESTATIONS REALISEES BY ID INDIVIDU :
/prestationsrealisees?idindividu={id}

FIND PRESTATIONS REALISEES BY ID MENAGE :
/prestationsrealisees?idmenage={id}

FIND PRESTATIONS REALISEES BY ID PRESTATION :
/prestationsrealisees?idprestation={id}


FIND ACTES REALISES BY ID UTILISATEUR :
/actesrealisees?idutilisateur={id}

FIND ACTES REALISES BY ID INDIVIDU :
/actesrealisees?idindividu={id}

FIND ACTES REALISES BY ID MENAGE :
/actesrealisees?idmenage={id}

FIND ACTES REALISES BY ID ACTE :
/actesrealisees?idacte={id}

FIND ACTES REALISES BY ID BESOIN :
/actesrealisees?idbesoin={id}

FIND ACTES REALISES BY ID PRESTATION :
/actesrealisees?idprestation={id}


FIND MENAGE BY ID REFERENT:
/menages?idreferant={id}

FIND MENAGE BY INDIVIDU NAME:
/individus?menage={nomUsage}

FIND INDIVIDUS BY ID MENAGE:
/individus?idmenage={id}


FIND LANGUES INDIVIDUS BY ID LANGUE :
/languesindividus?idlangue={id}

FIND LANGUES INDIVIDUS BY ID INDIVIDU :
/languesindividus?idindividu={id}

FIND NATIONNALITES INDIVIDUS BY ID NATIONNALITE :
/nationnalitesindividus?idnationnalite={id}

FIND NATIONNALITES INDIVIDUS BY ID INDIVIDU :
/nationnalitesindividus?idindividu={id}

FIND RESSOURCES INDIVIDUS BY ID INDIVIDU :
/ressourcesindividus?idindividu={id}

FIND RESSOURCES INDIVIDUS BY ID RESSOURCE :
/ressourcesindividus?idressource={id}

FIND RESSOURCES MENAGES BY ID MENAGE :
/ressourcesmenages?idmenage={id}

FIND RESSOURCES MENAGES BY ID RESSOURCE :
/ressourcesmenages?idressource={id}



**POST**

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



**PUT**

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