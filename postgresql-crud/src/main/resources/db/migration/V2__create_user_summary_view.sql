/* creates a reusable read-only representation of user information */
CREATE VIEW user_summary AS
SELECT
    id,
    name,
    email,
    name || ' <' || email || '>' AS display_name
FROM users;
