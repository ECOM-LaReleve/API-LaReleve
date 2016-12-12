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

Request | Route | Description |
------- | ----- | ----------- |
GET | /utilisateurs | Find all
GET | /utilisateurs/{id} | Find by id
POST | /utilisateurs | |
PUT | /utilisateurs/{id} | |


**Individus**

Request | Route | Description |
------- | ----- | ----------- |
GET | /individus | Find all
GET | /individus/{id} | Find by id
GET | /individus?menage={nomUsage} | Find menage by individu name
GET | /individus?idmenage={id} | Find individu by id menage
POST | /individus | |
PUT | /individus/{id} | |


**Menages**

Request | Route | Description |
------- | ----- | ----------- |
GET | /menages | Find all
GET | /menages/{id} | Find by id
GET | /menages?idreferant={id} | Find menage by id referent
POST | /menages | |
PUT | /menages/{id} | |


**Poles**

Request | Route | Description |
------- | ----- | ----------- |
GET | /poles | Find all
GET | /poles/{id} | Find by id
POST | /poles | |
PUT | /poles/{id} | |


**Services**

Request | Route | Description |
------- | ----- | ----------- |
GET | /services | Find all
GET | /services/{id} | Find by id
POST | /services | |
PUT | /services/{id} | |


**Besoins**

Request | Route | Description |
------- | ----- | ----------- |
GET | /besoins | Find all
GET | /besoins/{id} | Find by id
POST | /besoins | |
PUT | /besoins/{id} | |


**Prestations**

Request | Route | Description |
------- | ----- | ----------- |
GET | /prestations | Find all
GET | /prestations/{id} | Find by id
POST | /prestations | |
PUT | /prestations/{id} | |


**Prestations réalisées**

Request | Route | Description |
------- | ----- | ----------- |
GET | /prestationsrealisees | Find all 
GET | /prestationsrealisees/{id} | Find by id
GET | /prestationsrealisees?idutilisateur={id} | Find prestations realisees by id utilisateur
GET | /prestationsrealisees?idindividu={id} | Find prestations realisees by id individu
GET | /prestationsrealisees?idmenage={id} | Find prestations realisees by id menage
GET | /prestationsrealisees?idprestation={id} | Find prestations realisees by id prestation
POST | /prestationsrealisees | |
PUT | /prestationsrealisees/{id} | |


**Actes**

Request | Route | Description |
------- | ----- | ----------- |
GET | /actes | Find all
GET | /actes/{id} | Find by id
POST | /actes | |
PUT | /actes/{id} | |


**Actes réalisés**

Request | Route | Description |
------- | ----- | ----------- |
GET | /actesrealises | Find all
GET | /actesrealises/{id} | Find by id
GET | /actesrealisees?idutilisateur={id} | Find actes realises by id utilisateur
GET | /actesrealisees?idindividu={id} | Find actes realises by id individu
GET | /actesrealisees?idmenage={id} | Find actes realises by id menage
GET | /actesrealisees?idacte={id} | Find actes realises by id acte
GET | /actesrealisees?idbesoin={id} | Find actes realises by id besoin
GET | /actesrealisees?idprestation={id} | Find actes realises by id prestation
POST | /actesrealises | |
PUT | /actesrealises/{id} | |


**Logements**

Request | Route | Description |
------- | ----- | ----------- |
GET | /logements | Find all
GET | /logements/{id} | Find by id
POST | /logements | |
PUT | /logements/{id} | |


**Langues**

Request | Route | Description |
------- | ----- | ----------- |
GET | /langues | Find all
GET | /langues/{id} | Find by id
POST | /langues | |
PUT | /langues/{id} | |


**Langues individus**

Request | Route | Description |
------- | ----- | ----------- |
GET | /languesindividus | Find all
GET | /languesindividus/{id} | Find by id
GET | /languesindividus?idlangue={id} | Find langues individus by id langue
GET | /languesindividus?idindividu={id} | Find langues individus by id individu
POST | /languesindividus | |
PUT | /languesindividus/{id} | |


**Nationnalités**

Request | Route | Description |
------- | ----- | ----------- |
GET | /nationnalites | Find all 
GET | /nationnalites/{id} | Find by id
POST | /nationnalites | |
PUT | /nationnalites/{id} | |


**Nationnalités individus**

Request | Route | Description |
------- | ----- | ----------- |
GET | /nationnalitesindividus | Find all
GET | /nationnalitesindividus/{id} | Find by id
GET | /nationnalitesindividus?idnationnalite={id} | Find nationnalites individus by id nationnalite
GET | /nationnalitesindividus?idindividu={id} | Find nationnalites individus by id individu
POST | /nationnalitesindividus | |
PUT | /nationnalitesindividus/{id} | |


**Ressources**

Request | Route | Description |
------- | ----- | ----------- |
GET | /ressources | Find all
GET | /ressources/{id} | Find by id
POST | /ressources | |
PUT | /ressources/{id} | |


**Ressources individus**

Request | Route | Description |
------- | ----- | ----------- |
GET | /ressourcesindividus | Find all
GET | /ressourcesindividus/{id} | Find by id 
GET | /ressourcesindividus?idindividu={id} | Find ressource individus by id individu
GET | /ressourcesindividus?idressource={id} | Find ressource individus by id ressource
POST | /ressourcesindividus | |
PUT | /ressourcesindividus/{id} | |


**Ressources menages**

Request | Route | Description |
------- | ----- | ----------- |
GET | /ressourcesmenages | Find all
GET | /ressourcesmenages/{id} | Find by id
GET | /ressourcesmenages?idmenage={id} | Find ressource menages by id menage
GET | /ressourcesmenages?idressource={id} | Find ressource menages by id ressource
POST | /ressourcesmenages | |
PUT | /ressourcesmenages/{id} | |

