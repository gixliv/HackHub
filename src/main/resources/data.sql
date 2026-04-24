--utenti generici
INSERT INTO utenti (username, nome, cognome, sesso, email, password, telefono, iban, data_nascita, ruolo) VALUES
('MarioR', 'Mario', 'Rossi', 'M', 'mariorossi@gmail.com', 'password123', '1234567890', 'IT1234567890123456789012345', '1990-01-01', 'UTENTE_GENERICO'),
('LucaG', 'Luca', 'Gialli', 'M', 'lucagialli@gmail.com', 'password456', '0987654321', 'IT9876543210987654321098765', '1995-05-15', 'UTENTE_GENERICO'),
('SaraB', 'Sara', 'Bianchi', 'F', 'sarabianchi@gmail.com', 'password789', '5555555555', 'IT5555555555555555555555555', '1988-12-31', 'UTENTE_GENERICO');

--team
insert into teams (nome, descrizione, numero_massimo_componenti) VALUES
('team1', 'il primo team', 5);

--membri team
insert into utenti(username, nome, cognome, sesso, email, password, telefono, iban, data_nascita, ruolo, team_id) VALUES
('GinevraV', 'Ginevra', 'Verdi', 'F', 'ginevraverdi@gmail.com', 'password123', '4456546568', 'IT1234567890123456789012346', '1990-01-01', 'CREATORE_TEAM', 1),
('AntonioM', 'Antonio', 'Marroni', 'M', 'antoniomarroni@gmail.com', 'password456', '4984819131', 'IT9876543210987654321098766', '1995-05-15', 'MEMBRO_TEAM', 1);

update teams
set creatore_id = 4
where id = 1;

--utenti membri staff
insert into utenti (username, nome, cognome, sesso, email, password, telefono, iban, data_nascita, ruolo) VALUES
('Giudice1', 'Marco', 'Neri', 'M', 'marconeri@staff.it', 'pass1', '1111111111', 'IT1111111111111111111111111', '1980-01-01', 'GIUDICE'),
('Organizzatore1', 'Anna', 'Viola', 'F', 'annaviola@staff.it', 'pass2', '2222222222', 'IT2222222222222222222222222', '1985-01-01', 'ORGANIZZATORE'),
('Mentore1', 'Paolo', 'Verdi', 'M', 'paoloverdi@staff.it', 'pass3', '3333333333', 'IT3333333333333333333333333', '1982-03-10', 'MENTORE'),
('Mentore2', 'Elena', 'Neri', 'F', 'elenaneri@staff.it', 'pass4', '4444444444', 'IT4444444444444444444444444', '1987-07-22', 'MENTORE'),
('Organizzatore2', 'Sara', 'Gialli', 'F', 'saragialli@staff.it', 'pass5', '22222222226', 'IT55555555555555555555', '1986-01-01', 'ORGANIZZATORE')
;

--hackathon
insert into hackathons (nome, scadenza_iscrizioni, data_inizio, data_fine, luogo, dimensione_max_team, num_max_team, stato) values
    ('hackathon1', '2026-06-01', '2026-06-02', '2026-06-12', 'camerino', 5, 5, 'IN_ISCRIZIONE');

insert into membro_staff (id_utente, hackathon_id, codice_fiscale) VALUES
(6, 1, 'AAH97KJNKJEN567HA'),
(7, 1, 'AAH97KTRETRT567DS'),
(8, 1, 'AAH97LJNLJEZ567OA'),
(9,null,'AAH97LJNLJEZ567QE')
;

update hackathons
set organizzatore_id = 7, GIUDICE_ID=6
where id = 1;


insert into hackathon_mentori(mentore_id, hackathon_id) values
(8, 1);




