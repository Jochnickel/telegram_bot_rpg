

class Entity:
    entity_id = None
    name = None
    description = None
    linked_entities = []
    
    def __init__(self, entity_id, name, description, linked_entities):
        self.entity_id = entity_id
        self.name = name
        self.description = description
        self.linked_entities += linked_entities
    
    def getEntityID(self):
        return self.entity_id