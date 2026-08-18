/* creates a stored procedure that updates one user inside PostgreSQL */
CREATE PROCEDURE update_user_email(
    IN p_user_id BIGINT,
    IN p_new_email TEXT
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE users
    SET email = p_new_email
    WHERE id = p_user_id;

    /* P0002 represents that the requested row was not found */
    IF NOT FOUND THEN
        RAISE EXCEPTION 'User with id % was not found', p_user_id
            USING ERRCODE = 'P0002';
    END IF;
END;
$$;
