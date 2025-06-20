FROM node:14.9.0 AS builder
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci --prefer-offline --no-audit

COPY public ./public
COPY src    ./src
RUN npm run build

FROM node:14.9.0-slim
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci --omit=dev --prefer-offline --no-audit

COPY --from=builder /app/build ./build

EXPOSE 3000

CMD ["npm", "run", "start"]
