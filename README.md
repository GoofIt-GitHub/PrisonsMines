# PrisonsMines

PrisonsMines is a mine plugin that allows for the server to take on the functionality of a normal prisons server mine at the location they specify in the config.yml

### Functionality
A mine contains "target blocks" that can be defined in the config. These "target blocks" are the blocks that contain the chance of an ore spawning on them.
Mines reset after a certain amount of time specified in the config, and once the mine is reset the timer is reset and 3/4 of the target blocks turn into the designated ore

Once the plugin is started the mines all presume the state of "Stopped" which causes the countdown to not commence.
If the start command is called the timer will start and the state will now be set to "Running"
If the command stop is called then the state will be set to stopped and the timer will be paused at whatever it was at

### Commands
- /mine reset (mine_name)
- /mine stop (mine_name)
- /mine start (mine_name)
- /mine list

### Mines
- Diamond
- Gold
- Iron
- Redstone
- Coal

