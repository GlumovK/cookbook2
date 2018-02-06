DELETE FROM ingredient_to_recipe;
DELETE FROM catalog_to_recipe;
DELETE FROM ingredients;
DELETE FROM catalogs;
DELETE FROM recipes;
DELETE FROM users;




INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO recipes (name, description, cookAlgorithm, rating, user_id) VALUES
  ('Borsch', 'Суп со свеклой', 'алгоритм борща', 20,1),
  ('Mushroom soup', 'Суп с грибами', 'алгоритм грибовницы', 15, 1),
  ('Olivie', 'Салат с мясом', 'алгоритм оливье', 26, 2),
  ('Omelette', 'Жареные яйца', 'алгоритм яишницы', 11, 2),
  ('Cappuccino', 'Кофе с молоком', 'алгоритм капучино', 42, 2);

INSERT INTO catalogs (name) VALUES
  ('Soup'),
  ('Second course'),
  ('Snack'),
  ('Salad'),
  ('Drink');

INSERT INTO ingredients (name) VALUES
  ('Potatoes'),
  ('Beet'),
  ('Mushroom'),
  ('Meat'),
  ('Pease'),
  ('Egg'),
  ('Coffee');

INSERT INTO catalog_to_recipe (catalog_id, recipe_id) VALUES
  (1, 1),
  (1, 2),
  (2, 4),
  (3, 4),
  (4, 3),
  (5, 5);

INSERT INTO ingredient_to_recipe (ingredient_id, recipe_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 1),
  (3, 2),
  (4, 1),
  (4, 3),
  (5, 3),
  (6, 4),
  (7, 5);
