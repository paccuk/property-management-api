#!/bin/sh

echo "Starting PostgreSQL server..."
docker-entrypoint.sh postgres &

echo "Waiting for PostgreSQL to initialize..."
until pg_isready -h localhost -p 5432; do
    sleep 1
done
echo "PostgreSQL is ready."

echo "Initializing database..."
psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -f /docker-entrypoint-initdb.d/init-database.sql

if [ "$DEV" = "true" ]; then
    echo "Seeding database with test data..."
    psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -f /docker-entrypoint-initdb.d/seed-data.sql
fi
echo "Database initialization complete."

wait