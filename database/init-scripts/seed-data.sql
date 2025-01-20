INSERT INTO "property_management"."auth_user" (user_id, email, password, role)
VALUES
    ('e1111111-1111-1111-1111-111111111111', 'admin@example.com', 'hashed_password1', 'admin'),
    ('e2222222-2222-2222-2222-222222222222', 'user1@example.com', 'hashed_password2', 'user'),
    ('e3333333-3333-3333-3333-333333333333', 'user2@example.com', 'hashed_password3', 'user')
    ON CONFLICT (user_id) DO NOTHING;

INSERT INTO "property_management"."user" (user_id, first_name, last_name, email, phone)
VALUES
    ('e1111111-1111-1111-1111-111111111111', 'Admin', 'User', 'admin@example.com', '123-456-7890'),
    ('e2222222-2222-2222-2222-222222222222', 'John', 'Doe', 'user1@example.com', '234-567-8901'),
    ('e3333333-3333-3333-3333-333333333333', 'Jane', 'Doe', 'user2@example.com', '345-678-9012')
    ON CONFLICT (user_id) DO NOTHING;

-- Insert test properties
INSERT INTO "property_management"."property" (property_id, owner_id, name, location_address, location_city, location_postal_code, type, status, rental_limit)
VALUES
    ('p1111111-1111-1111-1111-111111111111', 'e2222222-2222-2222-2222-222222222222', 'Green Villa', '123 Green St', 'Green City', '12345', 'Apartment', 'Available', 5),
    ('p2222222-2222-2222-2222-222222222222', 'e3333333-3333-3333-3333-333333333333', 'Blue Villa', '456 Blue St', 'Blue City', '67890', 'House', 'PartiallyOccupied', 10),
    ('p3333333-3333-3333-3333-333333333333', 'e2222222-2222-2222-2222-222222222222', 'Yellow Villa', '789 Yellow St', 'Yellow City', '11223', 'Condo', 'FullyOccupied', 3)
    ON CONFLICT (property_id) DO NOTHING;