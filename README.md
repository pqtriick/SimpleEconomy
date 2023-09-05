# Simple Economy

A simple economy plugin with MySQL support and inbuilt API

# MySQL Config (Currently only MySQL Support :/)
```
mysql:
  enabled: 'TRUE'
  host: HOST
  user: USER
  pass: PASS
  databank: DB
  autoreconnect: 'FALSE'
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

Comming soon:
- Saving Data in Config files
- Language Support
