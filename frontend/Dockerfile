FROM node:14.9.0 AS builder
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci --prefer-offline --no-audit

COPY public ./public
COPY src    ./src

RUN npm run build


FROM node:14.9.0-slim AS runtime
WORKDIR /app

COPY --from=builder /app/node_modules ./node_modules

COPY --from=builder /app/build      ./build

COPY package.json ./


EXPOSE 3000
CMD ["npm", "run", "start"]
