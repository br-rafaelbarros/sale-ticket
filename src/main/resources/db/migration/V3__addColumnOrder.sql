-- add external id column to orders table
-- Add the updated_at column
ALTER TABLE ticket_system.orders
ADD external_id VARCHAR(255) NOT NULL;