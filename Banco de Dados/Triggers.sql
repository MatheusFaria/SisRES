USE sisres_db;

DROP TRIGGER trig_aluno_delete;
DROP TRIGGER trig_professor_delete;
DROP TRIGGER trig_equipamento_delete;
DROP TRIGGER trig_sala_delete;

DELIMITER |

CREATE TRIGGER trig_aluno_delete BEFORE DELETE ON aluno
  FOR EACH ROW BEGIN
    DELETE FROM reserva_sala_aluno WHERE id_aluno = OLD.id_aluno;
  END |

CREATE TRIGGER trig_professor_delete BEFORE DELETE ON professor
  FOR EACH ROW BEGIN
    DELETE FROM reserva_sala_professor WHERE id_professor = OLD.id_professor;
    DELETE FROM reserva_equipamento 
			WHERE id_professor = OLD.id_professor;
  END |

CREATE TRIGGER trig_sala_delete BEFORE DELETE ON sala
  FOR EACH ROW BEGIN
    DELETE FROM reserva_sala_aluno WHERE id_sala = OLD.id_sala;
    DELETE FROM reserva_sala_professor WHERE id_sala = OLD.id_sala;
  END |

CREATE TRIGGER trig_equipamento_delete BEFORE DELETE ON equipamento
  FOR EACH ROW BEGIN
    DELETE FROM reserva_equipamento 
			WHERE id_equipamento = OLD.id_equipamento;
  END |

DELIMITER ;