-- Insert sample data
INSERT INTO users (name, email, phone) VALUES
('John Doe', 'john.doe@example.com', '1234567890'),
('Jane Smith', 'jane.smith@example.com', '0987654321'),
('Bob Johnson', 'bob.johnson@example.com', '5555555555'),
('Alice Brown', 'alice.brown@example.com', '1111111111'),
('Charlie Wilson', 'charlie.wilson@example.com', '2222222222');

-- Verify the data
SELECT * FROM users;
