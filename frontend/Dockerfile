FROM node:14.9.0 AS builder
WORKDIR /app

RUN npm install -g yarn@1.22.22

COPY package.json yarn.lock ./

RUN --mount=type=cache,id=yarn,target=/root/.cache/yarn \
    yarn install --frozen-lockfile

COPY public ./public
COPY src    ./src

RUN yarn build

FROM node:14.9.0-slim AS runtime
WORKDIR /app

COPY --from=builder /app/node_modules ./node_modules

COPY --from=builder /app/build      ./build

COPY package.json ./

EXPOSE 3000
CMD ["npm", "run", "start"]
