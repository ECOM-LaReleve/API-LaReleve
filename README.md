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

Description | Route |
----------- | ----- |
Find all | /utilisateurs
Find by id | /utilisateurs/{id}
POST | /utilisateurs
PUT | /utilisateurs/{id}


**Individus**

Description | Route |
----------- | ----- |
Find all | /individus
Find by id | /individus/{id}
Find menage by individu name | /individus?menage={nomUsage}
Find individu by id menage | /individus?idmenage={id}
POST | /individus
PUT | /individus/{id}


**Menages**

Description | Route |
----------- | ----- |
Find all | /menages
Find by id | /menages/{id}
Find menage by id referent | /menages?idreferant={id}
POST | /menages
PUT |/menages/{id}


**Poles**

Description | Route |
----------- | ----- |
Find all | /poles
Find by id | /poles/{id}
POST | /poles
PUT | /poles/{id}


**Services**

Description | Route |
----------- | ----- |
Find all | /services
Find by id | /services/{id}
POST | /services
PUT | /services/{id}


**Besoins**

Description | Route |
----------- | ----- |
Find all | /besoins
Find by id | /besoins/{id}
POST | /besoins
PUT | /besoins/{id}


**Prestations**

Description | Route |
----------- | ----- |
Find all | /prestations
Find by id | /prestations/{id}
POST | /prestations
PUT | /prestations/{id}


**Prestations réalisées**

Description | Route |
----------- | ----- |
Find all | /prestationsrealisees
Find by id | /prestationsrealisees/{id}
Find prestations realisees by id utilisateur | /prestationsrealisees?idutilisateur={id}
Find prestations realisees by id individu | /prestationsrealisees?idindividu={id}
Find prestations realisees by id menage | /prestationsrealisees?idmenage={id}
Find prestations realisees by id prestation | /prestationsrealisees?idprestation={id}
POST | /prestationsrealisees
PUT | /prestationsrealisees/{id}


**Actes**

Description | Route |
----------- | ----- |
Find all | /actes
Find by id | /actes/{id}
POST | /actes
PUT | /actes/{id}


**Actes réalisés**

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
POST | /actesrealises
PUT | /actesrealises/{id}


**Logements**

Description | Route |
----------- | ----- |
Find all | /logements
Find by id | /logements/{id}
POST | /logements
PUT | /logements/{id}


**Langues**

Description | Route |
----------- | ----- |
Find all | /langues
Find by id | /langues/{id}
POST | /langues
PUT | /langues/{id}


**Langues individus**

Description | Route |
----------- | ----- |
Find all | /languesindividus
Find by id | /languesindividus/{id}
Find langues individus by id langue | /languesindividus?idlangue={id}
Find langues individus by id individu | /languesindividus?idindividu={id}
POST | /languesindividus
PUT | /languesindividus/{id}


**Nationnalités**

Description | Route |
----------- | ----- |
Find all | /nationnalites
Find by id | /nationnalites/{id}
POST | /nationnalites
PUT | /nationnalites/{id}


**Nationnalités individus**

Description | Route |
----------- | ----- |
Find all | /nationnalitesindividus
Find by id | /nationnalitesindividus/{id}
Find nationnalites individus by id nationnalite | /nationnalitesindividus?idnationnalite={id}
Find nationnalites individus by id individu | /nationnalitesindividus?idindividu={id}
POST | /nationnalitesindividus
PUT | /nationnalitesindividus/{id}


**Ressources**

Description | Route |
----------- | ----- |
Find all | /ressources
Find by id | /ressources/{id}
POST | /ressources
PUT | /ressources/{id}


**Ressources individus**

Description | Route |
----------- | ----- |
Find all | /ressourcesindividus
Find by id | /ressourcesindividus/{id}
Find ressource individus by id individu | /ressourcesindividus?idindividu={id}
Find ressource individus by id ressource | /ressourcesindividus?idressource={id}
POST | /ressourcesindividus
PUT | /ressourcesindividus/{id}


**Ressources menages**

Description | Route |
----------- | ----- |
Find all | /ressourcesmenages
Find by id | /ressourcesmenages/{id}
Find ressource menages by id menage | /ressourcesmenages?idmenage={id}
Find ressource menages by id ressource | /ressourcesmenages?idressource={id}
POST | /ressourcesmenages
PUT | /ressourcesmenages/{id}

