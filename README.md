Project Type: Telegram Bot

Responsibility: 15%

Project Lifetime: 3 Months - 2 Years

User Type 1: Content Creator

User Type 2: Casual Player

Main Language: Python

Core Features:
* Provide a Zork-Like MORPG-Environment
* Admin Controls: Debug the server entirely through telegram
* Admin Controls: Debug levels per person: Errors, Warnings, Info
* Account Management: Delete Own Account
* Account Management: Temporary Bans
* Rooms
* Items
* Players
* Spells/Actions
* Interface: Commands start with a dash "/"
* Interface: Commands may get piped (Using dash)
* Default Spells: Search Room
* Default Spells: Walk
* Default Spells: Talk
* Players have a location in active rooms
* Players can "search" the room
* Players can find every item within 1m
* Rooms can link to collectable and non-collectable items
* Items may be used without being collected
* Players can collect items
* Rooms, Items, Players, Spells are (unique) Entities
* There may exist multiple instances of items (mupltiple players may carry the same item)
* Entities have a name and a description
* Items can provide Functions e.g. Magnifier can inspect Room different.
* Players can cast spells if they are in their inventory (item) or if they are in them (room)
* There can be a major spell for an item
* Spells can remove Items
* Spells can create Items

Optional Features:
* Soundtrack files each same length (5min)
* Items have a vision range
* Players can see each other and use each others items
* Entities can be multiple types (Items and Rooms)
* Items can be hidden if the player is too far away.
* Limitations to Inventory
* Rooms on a 2D-Grid, Coordinates (Players can move within rooms)
* Rooms may or may not have Walls and Ceilings, but have at least 1cm² floor
* Rooms within Rooms are possible. They might not be visible.
* Rooms must have an exit
* Players can be Moderator
* Entities can be exported (Json or similar)

Prediction:
* Has to use Libraries
* Needs good Exception handling
* Needs Database
* Database needs to write files, telebot_db and game_db. telebot_db contains User info (Admin or kicked)
* no Root necessary

 
Roadmap:
* Try Telegram PIP Modules
* Hook it up with Errors
