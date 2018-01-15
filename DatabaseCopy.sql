--
-- File generated with SQLiteStudio v3.1.1 on Mon Jan 15 13:44:39 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Playlists
DROP TABLE IF EXISTS Playlists;
CREATE TABLE Playlists (playlistID INTEGER UNIQUE NOT NULL PRIMARY KEY, name VARCHAR UNIQUE NOT NULL, username VARCHAR REFERENCES Users (username) ON DELETE CASCADE ON UPDATE CASCADE MATCH SIMPLE NOT NULL, isUserEditable BOOLEAN NOT NULL);

-- Table: Playlists_Songs
DROP TABLE IF EXISTS Playlists_Songs;
CREATE TABLE Playlists_Songs (playlistID INTEGER REFERENCES Playlists (playlistID) NOT NULL, songID INTEGER REFERENCES Song (songID) NOT NULL, ID INTEGER PRIMARY KEY UNIQUE NOT NULL);

-- Table: Song
DROP TABLE IF EXISTS Song;
CREATE TABLE Song (songID INTEGER PRIMARY KEY UNIQUE NOT NULL, title VARCHAR UNIQUE NOT NULL, album VARCHAR, artist VARCHAR, length DECIMAL NOT NULL);

-- Table: Users
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (username VARCHAR UNIQUE NOT NULL PRIMARY KEY, password VARCHAR NOT NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
