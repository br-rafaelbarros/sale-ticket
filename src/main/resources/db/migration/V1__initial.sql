-- SCHEMA ticket_system
-- CREATE SCHEMA IF NOT EXISTS ticket_system;
-- clients table
CREATE TABLE IF NOT EXISTS ticket_system.clients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    document_number VARCHAR(14) NOT NULL,
    document_type INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN ticket_system.clients.document_type IS '1=PJ, 2=PF';
ALTER TABLE ticket_system.clients DROP CONSTRAINT IF EXISTS client_document_type_ck;
ALTER TABLE ticket_system.clients ADD CONSTRAINT client_document_type_ck CHECK (document_type IN (1, 2));

-- tickets table
CREATE TABLE IF NOT EXISTS ticket_system.tickets (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    status INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    qty_available INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN ticket_system.tickets.status IS '1=open to sale, 2=closed to sale';
ALTER TABLE ticket_system.tickets DROP CONSTRAINT IF EXISTS ticket_status_ck;
ALTER TABLE ticket_system.tickets ADD CONSTRAINT ticket_status_ck CHECK (status IN (1, 2));

-- -- orders table
CREATE TABLE IF NOT EXISTS ticket_system.orders (
    id SERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    ticket_id BIGINT NOT NULL,
    qty INTEGER NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    status INTEGER NOT NULL,
    pix_emv VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES ticket_system.clients(id),
    FOREIGN KEY (ticket_id) REFERENCES ticket_system.tickets(id)
);
COMMENT ON COLUMN ticket_system.orders.status IS '1=created, 2=paid, 3=canceled, 4=expired';
ALTER TABLE ticket_system.orders DROP CONSTRAINT IF EXISTS order_status_ck;
ALTER TABLE ticket_system.orders ADD CONSTRAINT order_status_ck CHECK (status IN (1, 2, 3, 4));

-- POPULATE TICKETS TABLE
INSERT INTO ticket_system.tickets (title, status, price, qty_available) VALUES
('Ticket 1', 1, 100.00, 100),
('Ticket 2', 1, 200.00, 100),
('Ticket 3', 1, 300.00, 100),
('Ticket 4', 1, 400.00, 100),
('Ticket 5', 1, 500.00, 100);