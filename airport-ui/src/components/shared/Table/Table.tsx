import React from 'react';
import { Table as TableBase } from 'antd';

import 'antd/lib/grid/style/index.less';

interface Props {
    loading?: boolean;
    columns: any;
    rows: any;
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
            onRow={(record, rowIndex) => {
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
