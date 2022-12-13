# Shortly

## Stack
- Kotlin
- Compose
- MVVM
- Hilt
- Realm
- Coroutines
- Flow
- Coil

## Architecture
- MVVM
- logic is organized in layers, all self contained, easily refactored, scaled and tested
- layers: ui, viewmodel, repository, datasource
- datasource: Retrofit for remote, Realm for local
- repository: magic happens here
- viewmodel: glue between repo and ui
- ui: compose
- package structure gives an ease of interacting with layers given that every feature has the above mentioned layer structured
- data is propagated thru the layers via Flows
- modularization would be an overkill here - if needed everything can be quickly refactored into different modules
- 
## Tests
- simple unit & ui tests present

## Video
https://drive.google.com/file/d/1Xe0oAITJUKCGpX9xwlJUypVTi4vDyed_