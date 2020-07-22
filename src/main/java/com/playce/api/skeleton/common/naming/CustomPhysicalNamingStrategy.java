/*
 * Copyright 2020 The Playce Project.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park	Jul 22, 2020	    First Draft.
 */
package com.playce.api.skeleton.common.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    /**
     * To physical catalog name identifier.
     *
     * @param identifier the identifier
     * @param jdbcEnv    the jdbc env
     * @return the identifier
     */
    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    /**
     * To physical column name identifier.
     *
     * @param identifier the identifier
     * @param jdbcEnv    the jdbc env
     * @return the identifier
     */
    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    /**
     * To physical schema name identifier.
     *
     * @param identifier the identifier
     * @param jdbcEnv    the jdbc env
     * @return the identifier
     */
    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    /**
     * To physical sequence name identifier.
     *
     * @param identifier the identifier
     * @param jdbcEnv    the jdbc env
     * @return the identifier
     */
    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    /**
     * To physical table name identifier.
     *
     * @param identifier the identifier
     * @param jdbcEnv    the jdbc env
     * @return the identifier
     */
    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    /**
     * Convert to snake case identifier.
     *
     * @param identifier the identifier
     * @return the identifier
     */
    private Identifier convertToSnakeCase(final Identifier identifier) {
        if (identifier != null) {
            final String regex = "([a-z])([A-Z])";
            final String replacement = "$1_$2";
            final String newName = identifier.getText()
                    .replaceAll(regex, replacement)
                    .toLowerCase();

            return Identifier.toIdentifier(newName);
        } else {
            return identifier;
        }
    }
}