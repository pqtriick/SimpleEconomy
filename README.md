# Simple Economy

A simple economy plugin with MySQL support and inbuilt API

# MySQL Config
```
mysql:
  enabled: 'TRUE'
  host: HOST
  user: USER
  pass: PASS
  databank: DB
  autoreconnect: 'FALSE'
```
# Normal Config Example(Without MySQL Support)

```
info:
  enabled: 'TRUE'
user:
  uuid:
    localmoney: '0'
    bankmoney: '0'
```

# Video

![](https://github.com/pqtriick/SimpleEconomy/blob/master/ezgif.com-video-to-gif%20(1).gif)

# Commands

#### /seco | See your local money.
#### /seco add [Player] [Bank/Local] [Amount] | Add money to a players local or bank account. (perm: seconomy.add)
#### /seco remove [Player] [Bank/Local] [Amount] | Remove money from a players local or bank account. (perm: seconomy.remove)
#### /seco info [Player] | See how much money the player has. (perm: seconomy.info)
#### /seco pay [Player] [Amount] | Give your local money to somebody else.
#### /secohelp | Opens the help page. (perm: seconomy.help)
#### /reloadconfig | Reloads the config

# Using the API

Simpy add the Plugin to your library and also your minecraft server.
To access the API you need to write the following code:

```java
Economy.getApi().()
```

You can use the following things:

```java
Economy.getApi().addBankmoney(UUID, Amount);
Economy.getApi().addLocalmoney(UUID, Amount);
Economy.getApi().removeBankmoney(UUID, Amount);
Economy.getApi().removeLocalmoney(UUID, Amount);
Economy.getApi().getBankmoney(UUID);
Economy.getApi().getLocalmoney(UUID);
```
# Messages

To edit the plugin messages, simply open the messages.yml file and edit them.
IMPORTANT: You need to keep the placeholders like %bank_money% or the plugin can't recognize the input.
IMPORTANT: Also reload the config with /reloadconfig !!




