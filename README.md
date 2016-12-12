# API-LaReleve

## Continuous integration
**Master :**
[![Build Status](https://travis-ci.org/ECOM-LaReleve/API-LaReleve.svg?branch=master)](https://travis-ci.org/ECOM-LaReleve/API-LaReleve)

**Develop :**
[![Build Status](https://travis-ci.org/ECOM-LaReleve/API-LaReleve.svg?branch=develop)](https://travis-ci.org/ECOM-LaReleve/API-LaReleve)

## Docker
[gattazr/lareleve](https://hub.docker.com/r/gattazr/lareleve/)


## Routes


**Utilisateurs**

GET

Description | Route |
----------- | ----- |
Find all | /utilisateurs
Find by id | /utilisateurs/{id}


POST

Route |
----- |
/utilisateurs

PUT

Route |
----- |
/utilisateurs/{id}


**Individus**

GET

Description | Route |
----------- | ----- |
Find all | /individus
Find by id | /individus/{id}
Find menage by individu name | /individus?menage={nomUsage}
Find individu by id menage | /individus?idmenage={id}

POST

Route |
----- |
/individus

PUT

Route |
----- |
/individus/{id}


**Menages**

GET

Description | Route |
----------- | ----- |
Find all | /menages
Find by id | /menages/{id}
Find menage by id referent | /menages?idreferant={id}

POST

Route |
----- |
/menages

PUT

Route |
----- |
/menages/{id}


**Poles**

GET

Description | Route |
----------- | ----- |
Find all | /poles
Find by id | /poles/{id}

POST

Route |
----- |
/poles

PUT

Route |
----- |
/poles/{id}


**Services**

GET

Description | Route |
----------- | ----- |
Find all | /services
Find by id | /services/{id}

POST

Route |
----- |
/services

PUT

Route |
----- |
/services/{id}


**Besoins**

GET

Description | Route |
----------- | ----- |
Find all | /besoins
Find by id | /besoins/{id}

POST

Route |
----- |
/besoins

PUT

Route |
----- |
/besoins/{id}


**Prestations**

GET

Description | Route |
----------- | ----- |
Find all | /prestations
Find by id | /prestations/{id}

POST

Route |
----- |
/prestations

PUT

Route |
----- |
/prestations/{id}


**Prestations réalisées**

GET

Description | Route |
----------- | ----- |
Find all | /prestationsrealisees
Find by id | /prestationsrealisees/{id}
Find prestations realisees by id utilisateur | /prestationsrealisees?idutilisateur={id}
Find prestations realisees by id individu | /prestationsrealisees?idindividu={id}
Find prestations realisees by id menage | /prestationsrealisees?idmenage={id}
Find prestations realisees by id prestation | /prestationsrealisees?idprestation={id}

POST

Route |
----- |
/prestationsrealisees

PUT

Route |
----- |
/prestationsrealisees/{id}


**Actes**

GET

Description | Route |
----------- | ----- |
Find all | /actes
Find by id | /actes/{id}

POST

Route |
----- |
/actes

PUT

Route |
----- |
/actes/{id}


**Actes réalisés**

GET

Description | Route |
----------- | ----- |
Find all | /actesrealises
Find by id | /actesrealises/{id}
Find actes realises by id utilisateur | /actesrealisees?idutilisateur={id}
Find actes realises by id individu | /actesrealisees?idindividu={id}
Find actes realises by id menage | /actesrealisees?idmenage={id}
Find actes realises by id acte | /actesrealisees?idacte={id}
Find actes realises by id besoin | /actesrealisees?idbesoin={id}
Find actes realises by id prestation | /actesrealisees?idprestation={id}

POST

Route |
----- |
/actesrealises

PUT

Route |
----- |
/actesrealises/{id}


**Logements**

GET

Description | Route |
----------- | ----- |
Find all | /logements
Find by id | /logements/{id}

POST

Route |
----- |
/logements

PUT

Route |
----- |
/logements/{id}


**Langues**

GET

Description | Route |
----------- | ----- |
Find all | /langues
Find by id | /langues/{id}

POST

Route |
----- |
/langues

PUT

Route |
----- |
/langues/{id}


**Langues individus**

GET

Description | Route |
----------- | ----- |
Find all | /languesindividus
Find by id | /languesindividus/{id}
Find langues individus by id langue | /languesindividus?idlangue={id}
Find langues individus by id individu | /languesindividus?idindividu={id}

POST

Route |
----- |
/languesindividus

PUT

Route |
----- |
/languesindividus/{id}


**Nationnalités**

GET

Description | Route |
----------- | ----- |
Find all | /nationnalites
Find by id | /nationnalites/{id}

POST

Route |
----- |
/nationnalites

PUT

Route |
----- |
/nationnalites/{id}


**Nationnalités individus**

GET

Description | Route |
----------- | ----- |
Find all | /nationnalitesindividus
Find by id | /nationnalitesindividus/{id}
Find nationnalites individus by id nationnalite | /nationnalitesindividus?idnationnalite={id}
Find nationnalites individus by id individu | /nationnalitesindividus?idindividu={id}

POST

Route |
----- |
/nationnalitesindividus

PUT

Route |
----- |
/nationnalitesindividus/{id}


**Ressources**

GET

Description | Route |
----------- | ----- |
Find all | /ressources
Find by id | /ressources/{id}

POST

Route |
----- |
/ressources

PUT

Route |
----- |
/ressources/{id}


**Ressources individus**

GET

Description | Route |
----------- | ----- |
Find all | /ressourcesindividus
Find by id | /ressourcesindividus/{id}
Find ressource individus by id individu | /ressourcesindividus?idindividu={id}
Find ressource individus by id ressource | /ressourcesindividus?idressource={id}

POST

Route |
----- |
/ressourcesindividus

PUT

Route |
----- |
/ressourcesindividus/{id}


**Ressources menages**

GET

Description | Route |
----------- | ----- |
Find all | /ressourcesmenages
Find by id | /ressourcesmenages/{id}
Find ressource menages by id menage | /ressourcesmenages?idmenage={id}
Find ressource menages by id ressource | /ressourcesmenages?idressource={id}

POST

Route |
----- |
/ressourcesmenages

PUT

Route |
----- |
/ressourcesmenages/{id}

