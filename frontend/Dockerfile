FROM node:14.9.0 AS builder
WORKDIR /app

RUN corepack enable

COPY yarn.lock package.json ./
RUN --mount=type=cache,id=yarn,target=/usr/local/share/.cache/yarn \
    yarn install --frozen-lockfile

COPY public ./public
COPY src    ./src

RUN yarn build
