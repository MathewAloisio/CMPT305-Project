# Planning
## Tooling
### API
- https://nobelprize.readme.io/v1.0

### Tools
- JavaFX
- GSON (to parse JSON data)


## Data (JSON)
### Prizes: http://api.nobelprize.org/v1/prize.json
- year
- category
- overallMotivation
- laureates: [ id, firstname, surname, motivation, share ]

### Laureates: http://api.nobelprize.org/v1/laureate.json
- id, firstname, surname, born, died, bornCountry, bornCountryCode, bornCity, diedCountry, diedCountryCode, diedCity, gender
- prizes: [ 
    + year
    + category
    + share
    + motivation
    + affiliations: [ name, city, country ]
]

### Countries: http://api.nobelprize.org/v1/country.json
- name, code


## Design
### Design Patterns
- MVC: separate UI and data logic
- Strategy: sorting criteria?

### API Requests
- put on a background thread

### Models
- Laureate
- Prize

### Edge Cases
- missing/corrupt data: 
- pagination/limiting # results per page:


## User Interface
### Views
- Overview: Categories + options (Gender, Country, Affiliation, Prize Category, Year)
    + search bar: laureate names, motivations?
    + sliders: prize year
- Results: filtered entries based on search criteria
    + tags: filter results based on selected tag(s)
- Biography: 1 entry, detailed information

### UI Components
- Sliders for date/year ranges?
- Sidebar
- Spinner (loading, network requests)
- Search Bar?
- Tags?

### Graphic Design
- primary/secondary font
- colour scheme