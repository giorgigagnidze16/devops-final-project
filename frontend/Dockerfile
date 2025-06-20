FROM node:20 AS builder
WORKDIR /app

COPY package.json yarn.lock ./

RUN --mount=type=cache,id=yarn,target=/root/.cache/yarn \
    yarn install --frozen-lockfile

COPY . .

RUN yarn build

FROM node:20-slim AS runtime
WORKDIR /app

COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/build      ./build

COPY package.json ./

EXPOSE 3000
CMD ["npm", "run", "start"]