FROM node:14.9.0 AS builder
WORKDIR /app

COPY package.json package-lock.json ./

RUN npm ci --prefer-offline --no-audit

COPY . .

RUN npm run build


FROM scratch AS artifacts
COPY --from=builder /app/build /build