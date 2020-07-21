package de.jj22.telegram_bot_rpg.Game.Entities;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EntityManager {
	final private GameDatabase gameDatabase;

	public EntityManager() throws SQLException {
		gameDatabase = new GameDatabase("game.db");
	}

	public void changeEntityDescription(Entity entity, String description) throws SQLException {
		gameDatabase.updateEntityDescription(entity.getEntityID(), description);
	}

	public void changeEntityName(Entity entity, String name) throws SQLException {
		gameDatabase.updateEntityName(entity.getEntityID(), name);
	}

	public Entity createEntity(String name, String description, Entity ...entities) throws SQLException {
		final var ent_id = gameDatabase.insertEntity(name, description).getInt(1);
		final var entity = new Entity(ent_id, name, description);
		setLinkedEntities(entity, entities);
		return entity;
	}


	public void setLinkedEntities(Entity entity, Entity ...entities) throws SQLException {
		final var entity_id = entity.getEntityID();
		gameDatabase.deleteEntityList(entity_id);
		if (null == entities) {
			return;
		}

		for (int i = 0; i < entities.length; i++) {
			final var e_id = entities[i].getEntityID();
			gameDatabase.insertEntityList(entity_id, i, e_id);
		}

	}

	public Player createPlayer(int player_id, Entity entity) throws PlayerAlreadyExistsException {
		try {
			gameDatabase.insertPlayer(player_id, entity.getEntityID());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlayerAlreadyExistsException();
		}
		return new Player(player_id, entity);
	}

	private Entity getEntity(int entity_id) throws SQLException, EntityNotFoundException {
		final var asd = gameDatabase.selectEntity(entity_id);
		if (asd.next()) {
			return new Entity(entity_id, asd.getString(2), asd.getString(3));
		} else {
			throw new EntityNotFoundException();
		}
	}

	public Entity getFirstEntity() throws SQLException, EntityNotFoundException {
		final var res = gameDatabase.selectFirstEntity();
		if (res.next()) {
			return getEntity(res.getInt(1));
		} else {
			throw new EntityNotFoundException();
		}
	}

	public Entity getLinkedEntity(Entity entity, int index) throws SQLException, EntityNotFoundException {
		final var entity_id = entity.getEntityID();
		final var res = gameDatabase.selectEntityList(entity_id, index);
		if (res.next()) {
			return getEntity(res.getInt(3));
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public List<Entity> getLinkedEntities(Entity entity) throws SQLException, EntityNotFoundException {
		final var out = new LinkedList<Entity>();
		try {
			for (var i = 0;; i++) {
				var e = getLinkedEntity(entity, i);
				out.add(e);
			}
		} catch (IndexOutOfBoundsException e) {
		}

		return out;
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

	public Entity setFirstEntity(Entity entity) throws SQLException {
		gameDatabase.setFirstEntity(entity.getEntityID());
		return entity;
	}

}
