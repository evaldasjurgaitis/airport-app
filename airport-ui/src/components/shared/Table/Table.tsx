import React from 'react';

import { Table as TableBase } from 'antd';
import { ColumnProps } from 'antd/es/table';

import 'antd/lib/grid/style/index.less';

import { AirportDetail } from '../../AirportApp/typing';

interface Props {
    loading?: boolean;
    columns: ColumnProps<AirportDetail>[];
    rows: AirportDetail[];
    pagination?: any;
    onChange?: (pagination: any) => void;
    onRowClick: (id: number) => void;
}

const Table = ({ loading, columns, rows, pagination, onChange, onRowClick }: Props) => {
    return (
        <TableBase
            rowKey={(record) => record.id}
            columns={columns}
            dataSource={rows}
            loading={loading}
            pagination={pagination}
            onChange={onChange}
            onRow={(record) => {
                return {
                    onClick: () => {
                        onRowClick(record.id);
                    },
                };
            }}
        />
    );
};

export default Table;
