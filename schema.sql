PRAGMA foreign_keys = ON;
CREATE TABLE traffic_system (
    system_id INTEGER PRIMARY KEY
);
CREATE TABLE traffic_signal (
    id INTEGER PRIMARY KEY,
    location TEXT,
    state TEXT,
    system_id INTEGER,
    FOREIGN KEY (system_id)
        REFERENCES traffic_system(system_id)
        ON DELETE CASCADE
);
CREATE TABLE traffic_sensor (
    id INTEGER PRIMARY KEY,
    location TEXT,
    status TEXT,
    system_id INTEGER,
    FOREIGN KEY (system_id)
        REFERENCES traffic_system(system_id)
        ON DELETE CASCADE
);
