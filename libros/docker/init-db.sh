#!/bin/bash
set -e

echo "Starting SQL Server..."

/opt/mssql/bin/sqlservr &

echo "Waiting for SQL Server to be ready..."
sleep 30s

echo "Running init.sql..."

/opt/mssql-tools18/bin/sqlcmd \
  -S localhost \
  -U sa \
  -P "S0luc10nPrueb4!" \
  -C \
  -i /database/init.sql

echo "Database initialized"

wait
