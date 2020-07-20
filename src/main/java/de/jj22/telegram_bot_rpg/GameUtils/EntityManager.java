package de.jj22.telegram_bot_rpg.GameUtils;

import java.sql.SQLException;

public class EntityManager {
	final private GameDatabase gameDatabase;

	public EntityManager() throws SQLException {
		gameDatabase = new GameDatabase("game.db");
	}

	public void changeEntityDescription(Entity entity, String description) throws SQLException {
		gameDatabase.updateEntityDescription(entity.getEntityID(), description);
	}

	public void changeEntityLinkedListItems(Entity entity, Entity[] entities) throws SQLException {
		deleteLinkedList(entity.getLinkedListID());
		final var ll_id = createEntityList(entities);
		gameDatabase.updateEntityLinkedListID(entity.getEntityID(), ll_id);
	}

	public void changeEntityName(Entity entity, String name) throws SQLException {
		gameDatabase.updateEntityName(entity.getEntityID(), name);
	}

	public Entity createEntity(String name, String description, Entity[] entities) throws SQLException {
		final var list_id = createEntityList(entities);
		final var ent_id = gameDatabase.insertEntity(name, description, list_id).getInt(1);
		return new Entity(ent_id, name, description, list_id);
	}

	private Integer createEntityList(Entity[] entities) throws SQLException {
		Integer list_id = null;
		if(null!=entities) {
			for (int i = entities.length - 1; 0 <= i; i--) {
				final var ent_id = entities[i].getEntityID();
				final var res = gameDatabase.insertEntityistItem(ent_id, list_id);
				res.next();
				list_id = res.getInt(1);
			}
		}
		return list_id;
	}

	public Player createPlayer(int player_id, Entity entity) throws SQLException {
		final var entity_id = entity.getEntityID();
		gameDatabase.insertPlayer(player_id, entity_id);
		return new Player(player_id, entity);
	}

	private void deleteLinkedList(int ll_id) throws SQLException {
		for (;;) {
			final var res = gameDatabase.selectEntityListItem(ll_id, 0);
			if (res.next()) {
				final var next_id = res.getInt(3);
				gameDatabase.deleteLinkedListItem(ll_id);
				ll_id = next_id;
			} else {
				break;
			}
		}
	}

	private Entity getEntity(int entity_id) throws SQLException, EntityNotFoundException {
		final var asd = gameDatabase.selectEntity(entity_id);
		if (asd.next()) {
			return new Entity(entity_id, asd.getString(2), asd.getString(3), asd.getInt(4));
		} else {
			throw new EntityNotFoundException();
		}
	}

	public Entity getFirstEntity() throws SQLException, EntityNotFoundException {
		final var res = gameDatabase.getFirstEntity();
		if (res.next()) {
			return getEntity(res.getInt(1));
		} else {
			throw new EntityNotFoundException();
		}
	}

	public Entity getLinkedEntity(Entity entity, int index) throws SQLException, EntityNotFoundException {
		final var ll_id = entity.getLinkedListID();
		final var res = gameDatabase.selectEntityListItem(ll_id, index);
		if (res.next()) {
			return getEntity(res.getInt(2));
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public Player getPlayer(int player_id) throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		final var sel = gameDatabase.selectPlayer(player_id);
		if (sel.next()) {
			final var entity = getEntity(sel.getInt(2));
			final var player = new Player(player_id, entity);
			return player;
		} else {
			throw new PlayerNotFoundException();
		}

	}

	public Entity setFirstEntity(String name, String description, Entity[] entities) throws SQLException {
		final var entity = createEntity(name, description, entities);
		gameDatabase.setFirstEntity(entity.getEntityID());
		return entity;
	}

}
