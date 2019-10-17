--
-- File generated with SQLiteStudio v3.2.1 on tor. okt. 17 12:01:00 2019
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Departures
CREATE TABLE Departures (
    DepTime   TIME    PRIMARY KEY,
    TrainID   INTEGER REFERENCES Trains (TrainID),
    StationID INTEGER REFERENCES Stations (StationID) 
);

INSERT INTO Departures (
                           DepTime,
                           TrainID,
                           StationID
                       )
                       VALUES (
                           '05:27',
                           1,
                           2
                       );

INSERT INTO Departures (
                           DepTime,
                           TrainID,
                           StationID
                       )
                       VALUES (
                           '06:00',
                           1,
                           1
                       );

INSERT INTO Departures (
                           DepTime,
                           TrainID,
                           StationID
                       )
                       VALUES (
                           '10:30',
                           1,
                           1
                       );


-- Table: STATIONS
CREATE TABLE STATIONS (
    StationID   INTEGER PRIMARY KEY,
    StationName TEXT
);

INSERT INTO STATIONS (
                         StationID,
                         StationName
                     )
                     VALUES (
                         1,
                         'København'
                     );

INSERT INTO STATIONS (
                         StationID,
                         StationName
                     )
                     VALUES (
                         2,
                         'Roskilde'
                     );


-- Table: Trains
CREATE TABLE Trains (
    TrainID INTEGER PRIMARY KEY,
    Route   TEXT
);

INSERT INTO Trains (
                       TrainID,
                       Route
                   )
                   VALUES (
                       1,
                       'København H-Odense'
                   );


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
