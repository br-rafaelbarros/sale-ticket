-- Add the updated_at column
ALTER TABLE ticket_system.orders
ADD updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;