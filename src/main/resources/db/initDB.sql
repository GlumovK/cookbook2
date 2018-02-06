DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS ingredient_to_recipe CASCADE;
DROP TABLE IF EXISTS catalog_to_recipe CASCADE;
DROP TABLE IF EXISTS recipes CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS catalogs;

CREATE TABLE users
(
  id        SERIAL PRIMARY KEY,
  name     VARCHAR                 NOT NULL,
  email    VARCHAR                 NOT NULL,
  password VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE recipes
(
  id            SERIAL PRIMARY KEY,
  name          VARCHAR NOT NULL,
  description   TEXT    NOT NULL,
  cookAlgorithm VARCHAR NOT NULL,
  rating        INT     NOT NULL,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX recipes_unique_user_name_idx ON recipes (user_id, name);


CREATE TABLE catalogs
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE ingredients
(
  id  SERIAL PRIMARY KEY,
  name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE catalog_to_recipe (
  id         SERIAL PRIMARY KEY,
  catalog_id INTEGER NOT NULL,
  recipe_id  INTEGER NOT NULL,
  UNIQUE (catalog_id, recipe_id),
  CONSTRAINT FK_catalog_id FOREIGN KEY (catalog_id) REFERENCES catalogs (id) ON DELETE CASCADE,
  CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX catalog_to_recipe_unique_catalog_recipe_idx ON catalog_to_recipe (catalog_id, recipe_id);

CREATE TABLE ingredient_to_recipe (
  id            SERIAL PRIMARY KEY,
  ingredient_id INTEGER NOT NULL,
  recipe_id     INTEGER NOT NULL,
  UNIQUE (ingredient_id, recipe_id),
  CONSTRAINT FK_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredients (id) ON DELETE CASCADE,
  CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX ingredient_to_recipe_unique_ingredient_recipe_idx ON ingredient_to_recipe (ingredient_id, recipe_id);
