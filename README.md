# simple-notes-application-291548-291557

Simple Notes Application - Backend (Spring Boot)

This container provides a REST API for managing notes with basic authentication stubs. It uses Spring Boot 3, H2 in-memory DB, and runs on port 3001.

How to run
- The CI will build and run this container automatically. Locally:
  - cd notes_backend
  - ./gradlew bootRun
- Visit Swagger UI: http://localhost:3001/docs
- Health: http://localhost:3001/health

Key Technologies
- Spring Boot Web, Data JPA
- H2 database (in-memory)
- springdoc OpenAPI
- Basic security configuration (password encoding, endpoints currently open)

Database
- H2 in-memory: jdbc:h2:mem:notesdb
- H2 console: http://localhost:3001/h2-console
  - JDBC URL: jdbc:h2:mem:notesdb
  - User: sa (no password)

API Endpoints

Notes
- GET /notes - List all notes
- GET /notes/{id} - Get note by id
- POST /notes - Create a note
  - Body: { "title": "My title", "content": "My content" }
- PUT /notes/{id} - Update a note
  - Body: { "title": "Updated title", "content": "Updated content" }
- DELETE /notes/{id} - Delete a note

Auth (stub - no tokens returned)
- POST /auth/signup
  - Body: { "username": "alice", "password": "s3cret" }
- POST /auth/login
  - Body: { "username": "alice", "password": "s3cret" }

Validation and Errors
- Missing title/content -> 400 with error details
- Not found -> 404
- Generic errors -> 500

Sample curl

# Create
curl -s -X POST http://localhost:3001/notes \
  -H "Content-Type: application/json" \
  -d '{"title":"Shopping","content":"Milk, Eggs"}' | jq

# List
curl -s http://localhost:3001/notes | jq

# Get by ID
curl -s http://localhost:3001/notes/1 | jq

# Update
curl -s -X PUT http://localhost:3001/notes/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated","content":"Updated content"}' | jq

# Delete
curl -s -X DELETE http://localhost:3001/notes/1 -i

# Signup
curl -s -X POST http://localhost:3001/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"s3cret"}' | jq

# Login
curl -s -X POST http://localhost:3001/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"s3cret"}' | jq

OpenAPI/Docs
- Swagger UI: /docs
- OpenAPI JSON: /api-docs

Security Notes
- For simplicity, all endpoints are currently permitted (no token issued on login). Passwords are stored encoded (BCrypt). To protect /notes with JWT or sessions, extend SecurityConfig and issue tokens in AuthService.

Environment
- No external secrets required. Port default is 3001 (configurable via server.port).